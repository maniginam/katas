(ns game-of-life
  (:require [clojure.set :as set]))

(defn get-neighbors [[x y]]
  #{[(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)]
    [(dec x) y] [(inc x) y]
    [(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]})

(defn count-neighbors [world cell]
  (count (filter world (get-neighbors cell))))

(defn evolve-cell [world [cell neighbor-counts]]
  (cond (= 3 neighbor-counts) cell
        (= 2 neighbor-counts) (when (contains? world cell) cell)))

(defn evolve [world]
  (let [neighbors (mapcat #(get-neighbors %) world)
        cells-to-evolve (set/union world neighbors)
        neighbor-counts (zipmap cells-to-evolve (map #(count-neighbors world %) cells-to-evolve))
        new-world (map #(evolve-cell world %) neighbor-counts)]
    (set (remove nil? new-world))))