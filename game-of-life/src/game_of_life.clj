(ns game-of-life
  (:require [clojure.set :as set]))

(defn get-neighbors [[x y]]
  #{[(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)]
    [(dec x) y] [(inc x) y]
    [(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]})

(defn count-neighbors [live-cells cell]
  (count (filter live-cells (get-neighbors cell))))

(defn evolve-cell [live-cells [cell neighbors]]
  (cond (= 3 neighbors) cell
        (= 2 neighbors) (when (contains? live-cells cell) cell)))

(defn evolve [live-cells]
  (let [live-cell-neighbors (mapcat #(get-neighbors %) live-cells)
        cells-to-evolve (set/union live-cells live-cell-neighbors)
        cell-map (zipmap cells-to-evolve (map #(count-neighbors live-cells %) cells-to-evolve))
        evolved-world (remove nil? (map #(evolve-cell live-cells %) cell-map))]
    (set evolved-world)))



