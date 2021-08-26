(ns game-of-life.core
  (:require [clojure.set :as set]))

(defn get-neighbors [[x y]]
  #{[(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)]
    [(dec x) y] [(inc x) y]
    [(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]})

(defn count-neighbors [world address]
  (count (filter world (get-neighbors address))))

(defn evolve-cells [world [address neighbor-count]]
  (cond (= 3 neighbor-count) address
        (and (contains? world address) (= neighbor-count 2)) address
        :else nil))

(defn evolve [world]
  (let [live-cell-neighbors (mapcat #(get-neighbors %) world)
        all-relevant-addresses (set/union world live-cell-neighbors)
        cells-to-evolve (map #(vector % (count-neighbors world %)) all-relevant-addresses)]
    (evolve-cells world cells-to-evolve)
    (set (remove nil? (map #(evolve-cells world %) cells-to-evolve)))))