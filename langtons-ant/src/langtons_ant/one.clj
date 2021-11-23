(ns langtons-ant.one)

(defn turn-clockwise [facing] (if (= 3 facing) 0 (inc facing)))
(defn turn-counter-clockwise [facing] (if (= 0 facing) 3 (dec facing)))

(defn turn! [{:keys [location facing] :as ant} world]
		(let [color         (get world location)
								new-direction (if (= :white color)
																								(turn-clockwise facing)
																								(turn-counter-clockwise facing))]
				(assoc ant :facing new-direction)))

(defn get-new-address [[x y] facing]
		(cond
				(= 0 facing) [x (dec y)]
				(= 1 facing) [(inc x) y]
				(= 2 facing) [x (inc y)]
				(= 3 facing) [(dec x) y]))

(defn move! [{:keys [location facing moves] :as ant}]
		(let [new-address (get-new-address location facing)]
				(assoc ant :location new-address :moves (if moves (inc moves) 1))))

(defn swap-color! [location world]
		(let [color (if (= :white (get world location)) :black :white)]
				(assoc-in world [location] color)))

(defn move-ant [{:keys [location] :as ant} world]
		[(-> (turn! ant world) move!) (swap-color! location world)])

(defn init-ant []
		{:x 4 :y 4 :location [4 4] :facing (rand-int 4)})

(defn init-world [size]
		(apply merge (for [row (range size) col (range size)] {[col row] :white})))

(defn -main [& args]
		(init-ant))
