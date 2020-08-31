(ns bowling.bowling)

(defn is-strike? [[roll1 & rolls]]
  (= 10 roll1))

(defn is-spare? [[roll1 roll2 & rolls]]
  (and (not (is-strike? rolls)) (= 10 (+ roll1 roll2))))

(defn score [game-score rolls]
    (cond (> 2 (count rolls)) game-score
          (is-strike? rolls) (score (apply + game-score (take 3 rolls)) (drop 1 rolls))
          (is-spare? rolls) (score (apply + game-score (take 3 rolls)) (drop 2 rolls))
          :else (score (apply + game-score (take 2 rolls)) (drop 2 rolls))))
