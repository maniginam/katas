(ns game-of-life
  (:require [clojure.set :as set]))

(defn get-neighbors [[x y]]
  #{[(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)]
    [(dec x) y] [(inc x) y]
    [(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]})

(defn count-live-neighbors [live-cells cell]
  (count (filter live-cells (get-neighbors cell))))

(defn evolve-cell [live-cells [cell neighbor-count]]
  (cond (= 3 neighbor-count) cell
        (and (= 2 neighbor-count) (contains? live-cells cell)) cell))

(defn evolve [live-cells]
  (let [neighbors (mapcat #(get-neighbors %) live-cells)
        relevant-cells (set/union live-cells neighbors)
        relevant-cells-with-neighbor-count (map #(vector % (count-live-neighbors live-cells %)) relevant-cells)
        new-world (remove nil? (map #(evolve-cell live-cells %) relevant-cells-with-neighbor-count))]
    (set new-world)))
