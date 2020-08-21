(ns bowling.bowling-3)

(defn calc-score [score rolls]
  (apply + score (take 2 rolls)))

(defn calc-bonus-score [score rolls]
  (apply + score (take 3 rolls)))

(defn this-frame [rolls]
  (take 2 rolls))

(defn is-spare? [frame]
  (= 10 (apply + frame)))

(defn is-strike? [frame]
  (= 10 (first frame)))

(defn score [rolls]
  (loop [rolls rolls frame 0 score 0]
    (cond (= frame 10) score
          (is-spare? (this-frame rolls)) (recur (drop 2 rolls) (inc frame) (calc-bonus-score score rolls))
          (is-strike? (this-frame rolls)) (recur (drop 1 rolls) (inc frame) (calc-bonus-score score rolls))
          :else (recur (drop 2 rolls) (inc frame) (calc-score score rolls)))))
