(ns bowling.bowling)

(defn score-each-frame [rolls]
  (loop [rolls rolls scores []]
    (cond (= 10 (count scores)) scores
          (= 10 (first rolls)) (recur (drop 1 rolls) (conj scores (apply + (take 3 rolls))))
          (= 10 (+ (first rolls) (second rolls))) (recur (drop 2 rolls) (conj scores (apply + (take 3 rolls))))
          :else (recur (drop 2 rolls) (conj scores (apply + (take 2 rolls)))))))

(defn score [rolls]
  (apply + (score-each-frame rolls)))
