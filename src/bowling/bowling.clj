(ns bowling.bowling)

(defn is-strike? [[roll1 & rolls]]
  (= 10 roll1))

(defn is-spare? [[roll1 roll2 & rolls]]
  (and (not (is-strike? rolls)) (= 10 (+ roll1 roll2))))

(defn score [rolls]
  (loop [rolls rolls frame 10 score 0]
    (cond (zero? frame) score
          (is-strike? rolls) (recur (drop 1 rolls) (dec frame) (apply + score (take 3 rolls)))
          (is-spare? rolls) (recur (drop 2 rolls) (dec frame) (apply + score (take 3 rolls)))
          :else (recur (drop 2 rolls) (dec frame) (apply + score (take 2 rolls))))))