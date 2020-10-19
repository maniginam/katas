(ns game-of-life.core
  (:require [clojure.set :as set]))

(defn get-neighbors [[x y]]
  #{[(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)]
    [(dec x) y] [(inc x) y]
    [(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]})

(defn count-neighbors [live-cells address]
  (count (filter live-cells (get-neighbors address))))

(defn evolve-cell [world [address count]]
  (cond (= count 3) address
        (and (contains? world address) (= count 2)) address
        :else nil))

(defn evolve-each-cell [world cells]
  (loop [cells cells
         new-world #{}]
    (if (empty? cells)
      new-world
      (let [[address count] (first cells)
            evolved-cell (evolve-cell world [address count])]
        (if (nil? evolved-cell)
          (recur (rest cells) new-world)
          (recur (rest cells) (conj new-world address)))))))

(defn evolve [world]
  (let [neighbors (mapcat #(get-neighbors %) world)
        addresses-to-evolve (set/union world neighbors)
        cells-to-evolve (map #(vector % (count-neighbors world %)) addresses-to-evolve)]
    (evolve-each-cell world cells-to-evolve)))