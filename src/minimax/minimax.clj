(ns minimax.minimax)

(def wins [[0 1 2] [3 4 5] [6 7 8] [0 3 6] [1 4 7] [2 5 8] [0 4 8] [2 4 6]])

(defn is-win? [board]
		(some true? (map #(and (= (nth board (first %)) (nth board (second %)))
																					(= (nth board (second %)) (nth board (last %)))) wins)))

(defn is-game-over? [board]
		(or (is-win? board) (every? string? board)))

(defn get-piece [player]
		(if (= 1 player) "X" "O"))

(defn set-score [board player depth]
		(if (is-win? board)
				(if (= 1 player) (- 10 depth) (- depth 10))
				0))

(defn minimax [player scores]
		(if (= 1 player)
				(apply min scores)
				(apply max scores)))

(defn score-boxes [board player depth]
		(for [box (filter integer? board)]
				(let [board (replace {box (get-piece player)} board)]
						(if (is-game-over? board)
								(set-score board player depth)
								(minimax player (score-boxes board (* -1 player) (inc depth)))))))

(defn is-best-box [score box-score box]
		(when (= score box-score) box))

(defn find-best-box [board player]
		(let [boxes      (filter integer? board)
								box-scores (score-boxes board player 0)
								best-score (minimax (* player -1) box-scores)
								best-boxes (remove nil? (map #(is-best-box best-score %1 %2) box-scores boxes))]
				(first best-boxes)))