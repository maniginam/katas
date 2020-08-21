(ns bowling.bowling-2)

(defn this-frame [rolls]
  (take 2 rolls))

(defn is-spare? [frame]
  (= 10 (apply + frame)))

(defn is-strike? [frame]
  (= 10 (first frame)))

(defn score-frame [rolls]
  (if (or (is-spare? (this-frame rolls)) (is-strike? (this-frame rolls)))
    (apply + (take 3 rolls))
    (apply + (take 2 rolls))))

(defn score [rolls]
  (loop [rolls rolls frame 0 score 0]
    (cond (= 10 frame) score
          (is-strike? (this-frame rolls)) (recur (drop 1 rolls) (inc frame) (+ score (score-frame rolls)))
          :else (recur (drop 2 rolls) (inc frame) (+ score (score-frame rolls))))))
