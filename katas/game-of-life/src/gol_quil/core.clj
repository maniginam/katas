(ns gol-quil.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [game-of-life.core :as life]
            [clojure.set :as set]))

(defn setup []
  (q/frame-rate 10)
  (q/color-mode :rgb)
  {:live-cells #{[10 0] [9 0] [8 0] [7 0] [6 0] [5 0] [4 0] [3 0] [2 0] [1 0] [0 0] [-1 0] [-2 0] [-3 0] [-4 0] [-5 0] [-6 0] [-7 0] [-8 0] [-9 0] [-10 0]}
   :x-range nil
   :y-range nil
   :cell-size 5})

(defn get-x-min [state]
  (let [world (:live-cells state)
        new-min (apply min (map #(first %) world))
        x-min (if (nil? (:x-range state)) new-min (first (:x-range state)))]
    (if (< new-min x-min) new-min x-min)))

(defn get-y-min [state]
  (let [world (:live-cells state)
        new-min (apply min (map #(second %) world))
        y-min (if (nil? (:y-range state)) new-min (first (:y-range state)))]
    (if (< new-min y-min) new-min y-min)))

(defn get-x-max [state]
  (let [world (:live-cells state)
        new-max (apply max (map #(first %) world))
        x-max (if (nil? (:x-range state)) new-max (second (:x-range state)))]
    (if (> new-max x-max) new-max x-max)))

(defn get-y-max [state]
  (let [world (:live-cells state)
        new-max (apply max (map #(second %) world))
        y-max (if (nil? (:y-range state)) new-max (second (:y-range state)))]
    (if (> new-max y-max) new-max y-max)))

(defn get-max-cell-count [state]
  (let [x-size (- (get-x-max state) (get-x-min state))
        y-size (- (get-y-max state) (get-y-min state))]
    (max x-size y-size)))

(defn update-state [state]
  {:live-cells (life/evolve (:live-cells state))
   :x-range [(get-x-min state) (get-x-max state)]
   :y-range [(get-y-min state) (get-y-max state)]
   :cell-size (/ 500 (get-max-cell-count state))})

(defn draw-state [state]
  (q/background 240)

  (let [min-cell (min (get-x-min state) (get-y-min state))
        max-cell (max (get-x-max state) (get-y-max state))
        live-cells (:live-cells state)
        dead-cells (for [x (range (- min-cell 2) (+ 4 max-cell))
                         y (range (- min-cell 2) (+ 4 max-cell))]
                     [x y])
        all-cells (set/union live-cells dead-cells)]
  (doseq [cell all-cells]
    (let [center (/ 500 2)
          cell-size (* 0.8 (:cell-size state))
          cell-radius (/ cell-size 2)
          x (+ center (- (* (first cell) cell-size) cell-radius))
          y (+ center (- (* (second cell) cell-size) cell-radius))]
      (if (contains? live-cells cell)
        (q/fill 0 255 0)
        (q/fill 0 0 0))
      (q/ellipse x y cell-size cell-size))))
  )


(q/defsketch gol-quil
  :title "The Game of Life"
  :size [500 500]
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
