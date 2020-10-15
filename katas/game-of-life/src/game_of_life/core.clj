(ns game-of-life.core
  (:require [clojure.set :as set]))

(defn get-neighbors [[x y]]
  #{[(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)]
    [(dec x) y] [(inc x) y]
    [(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]})

(defn count-neighbors [world address]
  (count (filter world (get-neighbors address))))

(defn evolve-cell [world [address count]]
  (cond (= count 3) address
        (and (contains? world address) (= 2 count)) address
        :else nil))

(defn live-by-rules-of-life [world cells]
  (loop [cells cells new-world #{}]
    (if (empty? cells)
      new-world
      (let [[address count] (first cells)
            evolved-cell (evolve-cell world [address count])]
        (if (nil? evolved-cell)
          (recur (rest cells) new-world)
          (recur (rest cells) (conj new-world address)))))))

(defn evolve [world]
  (let [neighbor-addresses (mapcat #(get-neighbors %) world)
        live-cells-and-neighbors (set/union world neighbor-addresses)
        live-cell-and-neighbor-counts (map #(vector % (count-neighbors world %)) live-cells-and-neighbors)]
    (live-by-rules-of-life world live-cell-and-neighbor-counts)))