(ns game-of-life.gol)



(defn get-neighbors [[x y]]
		#{[(dec x) (inc y)] [x (inc y)] [(inc x) (inc y)] [(dec x) y] [(inc x) y] [(dec x) (dec y)] [x (dec y)] [(inc x) (dec y)]})

(defn evolve-cell [cell world]
		(let [neighbors (get-neighbors cell)
								num-of-neighbors (count (filter world neighbors))]
				(or (when (= num-of-neighbors 3) cell)
						(when (and (= 2 num-of-neighbors) (contains? world cell)) cell))))

(defn evolve [world]
		(println "world: " world)
		(let [cell-neighbors (mapcat #(get-neighbors %) world)
								cells-to-evolve (apply conj world cell-neighbors)]
			(->> (map #(evolve-cell % world) cells-to-evolve)
				(remove nil?)
				set)))
