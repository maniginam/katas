(ns bowling.bowling-3)

(defn calc-score [score rolls]
  (apply + score (take 2 rolls)))

(defn calc-bonus-score [score rolls]
  (apply + score (take 3 rolls)))

(defn is-strike? [[roll1 & _ :as rolls]]
  (= 10 roll1))

(defn is-spare? [[roll1 roll2 & _ :as rolls]]
  (and (not (is-strike? rolls)) (= 10 (+ roll1 roll2))))

(defn score [rolls]
  (loop [rolls rolls frame 0 score 0]
    (cond (= frame 10) score
          (is-strike? rolls) (recur (drop 1 rolls) (inc frame) (calc-bonus-score score rolls))
          (is-spare? rolls) (recur (drop 2 rolls) (inc frame) (calc-bonus-score score rolls))
          :else (recur (drop 2 rolls) (inc frame) (calc-score score rolls)))))