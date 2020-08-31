(ns bowling.bowling)

(defn is-strike? [[roll1 & rolls]]
  (= 10 roll1))

(defn is-spare? [[roll1 roll2 & rolls]]
  (and (not (is-strike? rolls)) (= 10 (+ roll1 roll2))))

(defn score [current-score frames rolls]
    (cond (zero? frames) current-score
          (is-strike? rolls) (score (apply + current-score (take 3 rolls)) (dec frames) (drop 1 rolls))
          (is-spare? rolls) (score (apply + current-score (take 3 rolls)) (dec frames) (drop 2 rolls))
          :else (score (apply + current-score (take 2 rolls)) (dec frames) (drop 2 rolls))))
