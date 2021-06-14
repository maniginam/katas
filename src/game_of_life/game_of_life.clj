(ns game-of-life.game-of-life
  (:require [clojure.set :as set]))

(defn get-neighbors [[x y]]
  #{[(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)]
    [(dec x) y] [(inc x) y]
    [(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]})

(defn count-live-neighbors [world cell]
    (count (filter world (get-neighbors cell))))

(defn evolve-cell [world [cell neighbors]]
  (cond (= 3 neighbors) cell
        (= 2 neighbors) (when (contains? world cell) cell)))

(defn evolve [world]
  (let [neighbors (mapcat #(get-neighbors %) world)
        cells-to-evolve (set/union world neighbors)
        cells-and-neighbor-counts (zipmap cells-to-evolve (map #(count-live-neighbors world %) cells-to-evolve))
        new-world (map #(evolve-cell world %) cells-and-neighbor-counts)]
    (set (remove nil? new-world))))