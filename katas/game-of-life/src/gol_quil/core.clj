(ns gol-quil.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [game-of-life.core :as life]))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 20)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :rgb)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:live-cells #{[10 0] [9 0] [8 0] [7 0] [6 0] [5 0] [4 0] [3 0] [2 0] [1 0] [0 0] [-1 0] [-2 0] [-3 0] [-4 0] [-5 0] [-6 0] [-7 0] [-8 0] [-9 0] [-10 0]}
   :cell-size 5})

(defn get-x-min [world]
  (apply min (map #(first %) world)))

(defn get-y-min [world]
  (apply min (map #(second %) world)))

(defn get-x-max [world]
  (apply max (map #(first %) world)))

(defn get-y-max [world]
  (apply max (map #(second %) world)))

(defn get-max-cell-count [world]
  (let [x-size (- (get-x-max world) (get-x-min world))
        y-size (- (get-y-max world) (get-y-min world))]
    (max x-size y-size)))

(defn update-state [state]
  {:live-cells (life/evolve (:live-cells state))
   :x-range [(get-x-min (:live-cells state)) (get-x-max (:live-cells state))]
   :y-range [(get-y-min (:live-cells state)) (get-y-max (:live-cells state))]
   :cell-size (/ 500 (get-max-cell-count (:live-cells state)))})

(defn draw-state [state]
  (q/background 240)

  (doseq [cell (:live-cells state)]
    (let [center (/ 500 2)
          cell-size (* 0.5 (:cell-size state))
          cell-radius (/ cell-size 2)
          x (+ center (- (* (first cell) cell-size) cell-radius))
          y (+ center (- (* (second cell) cell-size) cell-radius))]
      (q/fill 0 255 0)
      (q/ellipse x y cell-size cell-size)))
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
