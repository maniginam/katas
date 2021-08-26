(ns minimax.minimax)

(def wins [[0 1 2] [3 4 5] [6 7 8] [0 3 6] [1 4 7] [2 5 8] [0 4 8] [2 4 6]])
(def pieces {1 "X" -1 "O"})

(defn is-win? [board]
		(some true? (map #(and (= (nth board (first %)) (nth board (second %)))
																						(= (nth board (second %)) (nth board (last %)))) wins)))

(defn is-game-over? [board]
		(or (is-win? board) (every? string? board)))

(defn open-boxes [board]
		(filter integer? board))

(defn set-score [board player depth]
		(if (is-win? board) (* (- 10 depth) player) 0))

(defn minimax [player scores]
		(if (= 1 player) (apply min scores) (apply max scores)))

(defn best-boxes [boxes box-scores best-score]
		(remove nil? (map #(when (= best-score %1) %2) box-scores boxes)))

(defn find-best-score [board player depth]
		(if (is-game-over? board)
				(set-score board player depth)
				(let [boards (map #(assoc board % (get pieces (* player -1))) (open-boxes board))
										scores (map #(find-best-score % (* player -1) (inc depth)) boards)]
						(minimax player scores))))

(defn find-best-box [board player]
		(let [boxes      (open-boxes board)
								boards     (map #(assoc board % (get pieces player)) boxes)
								box-scores (map #(find-best-score % player 0) boards)
								best-score (minimax (* player -1) box-scores)]
				(first (best-boxes boxes box-scores best-score))))