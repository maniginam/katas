(ns langtons-ant.gui
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [clojure.set :as set]
            [langtons-ant.two :as ant]))

(def size 1040)
(def world [])

(defn get-x-min [{:keys [dance] :as state}]
  (let [world   (:dark-cells dance)
        new-min (apply min (map #(first %) world))
        x-min   (if (nil? (:x-range state)) new-min (first (:x-range state)))]
    (if (< new-min x-min) new-min x-min)))

(defn get-y-min [{:keys [dance] :as state}]
  (let [world   (:dark-cells dance)
        new-min (apply min (map #(second %) world))
        y-min   (if (nil? (:y-range state)) new-min (first (:y-range state)))]
    (if (< new-min y-min) new-min y-min)))

(defn get-x-max [{:keys [dance] :as state}]
  (let [world   (:dark-cells dance)
        new-max (apply max (map #(first %) world))
        x-max   (if (nil? (:x-range state)) new-max (second (:x-range state)))]
    (if (> new-max x-max) new-max x-max)))

(defn get-y-max [{:keys [dance] :as state}]
  (let [world   (:dark-cells dance)
        new-max (apply max (map #(second %) world))
        y-max   (if (nil? (:y-range state)) new-max (second (:y-range state)))]
    (if (> new-max y-max) new-max y-max)))

(defn get-max-cell-count [[x-range y-range]]
  (let [x-size (- (apply max x-range) (apply min x-range))
        y-size (- (apply max y-range) (apply min y-range))]
    (max x-size y-size)))

(def steps 2000)

(defn setup []
  (q/frame-rate 5)
  (q/color-mode :rgb)
  (let [xs      (map first world)
        ys      (map second world)
        x-range [-10 10]
        y-range [-10 10]]
    {:ant [0 0]
     :dance {:steps steps :dark-cells [] :ant-cell [0 0] :prev-cell nil}
     :x-range x-range
     :y-range y-range
     :cell-size (/ (- size 100) (get-max-cell-count [x-range y-range]))}))

(defn update-state [{:keys [dance] :as state}]
  (println "(:dark-cells dance): " (:dark-cells dance))
  (let [xs      (map first (:dark-cells dance))
        ys      (map second (:dark-cells dance))
        x-range (if (or (empty? xs) (< (apply min xs) 10))
                  [-10 10]
                  [(if (< (- (apply min xs) 5) (first (:x-range state)))
                     (- (apply min xs) 5)
                     (first (:x-range state)))
                   (if (< (+ 5 (apply max xs)) (last (:x-range state)))
                     (+ 5 (apply max xs))
                     (last (:x-range state)))])
        y-range (if (or (empty? ys) (< (apply min ys) 10))
                  [-10 10]
                  [(if (< (- (apply min ys) 5) (first (:y-range state))) (- (apply min ys) 5) (first (:y-range state))) (if (< (+ 5 (apply max ys)) (last (:y-range state))) (+ 5 (apply max ys)) (last (:y-range state)))])
        dance (ant/gui-dance! dance)]
    {:ant (:ant-cell dance)
     :dance dance
     :x-range    x-range
     :y-range    y-range
     :cell-size  (/ (- size 100) (get-max-cell-count [x-range y-range]))}))

(defn draw-ant [x y cell-radius]
  (q/fill 0 0 0)
  (q/ellipse (+ x cell-radius) (- (+ y cell-radius) 8) 8 8)
  (q/ellipse (+ x cell-radius) (+ y cell-radius) 10 11)
  (q/ellipse (+ x cell-radius) (+ y cell-radius 11) 10 12)
  (q/line (+ x cell-radius) (+ y cell-radius) (- (+ x cell-radius) 10) (- (+ y cell-radius) 10))
  (q/line (+ x cell-radius) (+ y cell-radius) (+ (+ x cell-radius) 10) (+ (+ y cell-radius) 10))
  (q/line (+ x cell-radius) (+ y cell-radius) (- (+ x cell-radius) 10) (+ y cell-radius))
  (q/line (+ x cell-radius) (+ y cell-radius) (+ (+ x cell-radius) 10) (+ y cell-radius))
  (q/line (+ x cell-radius) (+ y cell-radius) (- (+ x cell-radius) 10) (- (+ y cell-radius) 10))
  (q/line (+ x cell-radius) (+ y cell-radius) (+ (+ x cell-radius) 10) (+ (+ y cell-radius) 10)))

(defn draw-state [{:keys [dance ant] :as state}]
  (q/background 46 47 51)

  (let [dark-cells (:dark-cells dance)
        xs         (map first dark-cells)
        ys         (map second dark-cells)
        min-cell   (if (or (empty? xs) (< (apply min xs) 10))
                     -10
                     (min (get-x-min state) (get-y-min state)))
        max-cell   (if (or (empty? xs) (< (apply min xs) 10))
                     10
                     (max (get-x-max state) (get-y-max state)))
        dead-cells (for [x (range (- min-cell 5) (+ 10 max-cell))
                         y (range (- min-cell 5) (+ 10 max-cell))]
                     [x y])
        all-cells  (set/union dark-cells dead-cells)]
    (doseq [cell all-cells]
      (let [center      (/ size 2)
            cell-size   (:cell-size state)
            cell-radius (/ cell-size 2)
            x           (+ center (- (* (first cell) cell-size) cell-radius))
            y           (+ center (- (* (second cell) cell-size) cell-radius))]
        (if-not (empty? (filter #(= cell %) dark-cells))
          (q/fill 255 166 77)
          (q/fill 46 47 51))
        (q/stroke 204 153 255)
        (q/rect x y cell-size cell-size)
        (when (= cell ant) (draw-ant x y cell-radius)))))
  )

(q/defsketch gui
             :title "Langton's Ant"
             :size [size size]
             ; setup function called only once, during sketch initialization.
             :setup setup
             ; update-state is called on each iteration before draw-state.
             :update update-state
             :draw draw-state
             :features [:keep-on-top]
             ; This sketch uses functional-mode middleware.
             ; Check quil wiki for more info about middlewares and particularly
             ; fun-mode.
             :middleware [m/fun-mode])

;(def new-world (atom [[10 0] [9 0] [8 0] [7 0] [6 0] [5 0] [4 0] [3 0] [2 0] [1 0] [0 0] [-1 0] [-2 0] [-3 0] [-4 0] [-5 0] [-6 0] [-7 0] [-8 0] [-9 0] [-10 0]]))
;
;(defn -main [world]
;  (reset! new-world world))