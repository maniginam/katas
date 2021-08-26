(ns game-of-life.gol-gui
		(:require [quil.core :as q]
												[quil.middleware :as m]
												[clojure.set :as set]
												[game-of-life.gol :as life]))

(def size 1040)
(def grid-size 50)
;(def world #{[10 0] [9 0] [8 0] [7 0] [6 0] [5 0] [4 0] [3 0] [2 0] [1 0] [0 0] [-1 0] [-2 0] [-3 0] [-4 0] [-5 0] [-6 0] [-7 0] [-8 0] [-9 0] [-10 0]
;													[0 10] [0 9] [0 8] [0 7] [0 6] [0 5] [0 4] [0 3] [0 2] [0 1] [0 -1] [0 -2] [0 -3] [0 -4] [0 -5] [0 -6] [0 -7] [0 -8] [0 -9] [0 -10]
;													[-6 5] [-5 5] [-4 5] [-5 6] [-5 4]
;													[6 5] [5 5] [4 5] [5 6] [5 4]
;													[-6 -5] [-5 -5] [-4 -5] [-5 -6] [-5 -4]
;													[6 -5] [5 -5] [4 -5] [5 -6] [5 -4]})

(def world #{})

(defn get-x-min [state]
		(let [world   (:live-cells state)
								new-min (if (empty? world) (* -1 grid-size) (apply min (map #(first %) world)))
								x-min   (if (nil? (:x-range state)) new-min (first (:x-range state)))]
				(if (< new-min x-min) new-min x-min)))

(defn get-y-min [state]
		(let [world   (:live-cells state)
								new-min (if (empty? world) (* -1 grid-size) (apply min (map #(first %) world)))
								y-min   (if (nil? (:y-range state)) new-min (first (:y-range state)))]
				(if (< new-min y-min) new-min y-min)))

(defn get-x-max [state]
		(let [world   (:live-cells state)
								new-max (if (empty? world) grid-size (apply max (map #(first %) world)))
								x-max   (if (nil? (:x-range state)) new-max (second (:x-range state)))]
				(if (> new-max x-max) new-max x-max)))

(defn get-y-max [state]
		(let [world   (:live-cells state)
								new-max (if (empty? world) grid-size (apply max (map #(first %) world)))
								y-max   (if (nil? (:y-range state)) new-max (second (:y-range state)))]
				(if (> new-max y-max) new-max y-max)))

(defn get-max-cell-count [[x-range y-range]]
		(let [x-size (- (apply max x-range) (apply min x-range))
								y-size (- (apply max y-range) (apply min y-range))]
				(max x-size y-size)))

(defn setup []
		(println "set up")
		(q/frame-rate 1)
		(q/color-mode :rgb)
		(let [xs      (if (empty? world) (range 6) (map first world))
								ys      (if (empty? world) (range 6) (map second world))
								;x-range [(apply min xs) (apply max xs)]
								;y-range [(apply min ys) (apply max ys)]
								x-range [(* -1 grid-size) grid-size]
								y-range [(* -1 grid-size) grid-size]
								]
				{;:live-cells (set @life/new-world)
					:state      :setup
					:live-cells world
					:x-range    x-range
					:y-range    y-range
					:cell-size  (/ (- size 100) (get-max-cell-count [x-range y-range]))}))

(defn update-state [state]
		(println "update state: " state)
		(let [live-cells (set (:live-cells state))
								xs         (map first live-cells)
								ys         (map second live-cells)
								x-range    (if (= :evolving (:state state)) [(if (< (- (apply min xs) 5) (first (:x-range state))) (- (apply min xs) 5) (first (:x-range state))) (if (< (+ 5 (apply max xs)) (last (:x-range state))) (+ 5 (apply max xs)) (last (:x-range state)))] [-10 10])
								y-range    (if (= :evolving (:state state)) [(if (< (- (apply min ys) 5) (first (:y-range state))) (- (apply min ys) 5) (first (:y-range state))) (if (< (+ 5 (apply max ys)) (last (:y-range state))) (+ 5 (apply max ys)) (last (:y-range state)))] [-10 10])]
				{:live-cells (cond (= :setup (:state state)) live-cells
																			(empty? live-cells) live-cells
																			:else (life/evolve (set (:live-cells state))))
					:state      (:state state)
					:x-range    x-range
					:y-range    y-range
					:cell-size  (/ (- size 100) (get-max-cell-count [x-range y-range]))}))

(defn set-cell [state cell]
		(let [center      (/ size 2)
								cell-size   (:cell-size state)
								cell-radius (/ cell-size 2)]
				[cell-size
					(+ center (- (* (first cell) cell-size) cell-radius))
					(+ center (- (* (second cell) cell-size) cell-radius))]
				))

(defn draw-state [state]
		(q/background 46 47 51)

		(let [min-cell   (min (get-x-min state) (get-y-min state))
								max-cell   (max (get-x-max state) (get-y-max state))
								live-cells (:live-cells state)
								dead-cells (for [x (range (- min-cell 5) (+ 10 max-cell))
																									y (range (- min-cell 5) (+ 10 max-cell))]
																					[x y])
								all-cells  (set/union live-cells dead-cells)]
				(doseq [cell all-cells]
						(let [[cell-size x y] (set-cell state cell)]
								(if (contains? live-cells cell)
										(q/fill 255 166 77)
										(q/fill 46 47 51))
								(q/stroke 204 153 255)
								(q/rect x y cell-size cell-size))))
		)

;(defmulti mouse-clicked (fn [state _] (:status state)))

(defn mouse-in-cell? [x-click y-click [cell-size x y]] (and (>= x-click x) (< x-click (+ x cell-size)) (>= y-click y) (< y-click (+ y cell-size))))

(defn mouse-clicked [state event]
		(println "state: " state)
		(let [x-click    (:x event)
								y-click    (:y event)
								min-cell   (min (get-x-min state) (get-y-min state))
								max-cell   (max (get-x-max state) (get-y-max state))
								live-cells (:live-cells state)
								dead-cells (for [x (range (- min-cell 5) (+ 10 max-cell))
																									y (range (- min-cell 5) (+ 10 max-cell))]
																					[x y])
								all-cells  (set/union live-cells dead-cells)
								cell       (first (filter #(mouse-in-cell? x-click y-click (set-cell state %)) all-cells))]
				(println "cell: " cell)
				(if-not (empty? (filter #(= cell %) live-cells))
						(assoc-in state [:live-cells] (remove #{cell} live-cells))
						(let [state (assoc-in state [:live-cells] (set/union live-cells #{cell}))]
								(println "live cell state: " state)
								state))))

(defn start-evolution! [state event]
		(println "state: " state)
		(println "event: " event)
		(if (= :setup (:state state))
				(assoc state :state :evolving)
				(assoc state :state :setup)))

(q/defsketch gui
		:title "The Game of Life"
		:size [size size]
		; setup function called only once, during sketch initialization.
		:setup setup
		; update-state is called on each iteration before draw-state.
		:update update-state
		:draw draw-state
		:features [:keep-on-top]
		:key-typed start-evolution!
		:mouse-clicked mouse-clicked
		; This sketch uses functional-mode middleware.
		; Check quil wiki for more info about middlewares and particularly
		; fun-mode.
		:middleware [m/fun-mode])

;(def new-world (atom [[10 0] [9 0] [8 0] [7 0] [6 0] [5 0] [4 0] [3 0] [2 0] [1 0] [0 0] [-1 0] [-2 0] [-3 0] [-4 0] [-5 0] [-6 0] [-7 0] [-8 0] [-9 0] [-10 0]]))
;
;(defn -main [world]
;  (reset! new-world world))