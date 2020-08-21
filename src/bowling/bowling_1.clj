(ns bowling.bowling-1)

(defn roll [rolls pins]
  (apply (partial conj rolls) pins))

(defn is-spare? [frame]
  (= 10 (apply + frame)))

(defn score [rolls n]
  (apply + (take n rolls)))

(defn is-strike? [roll]
  (= 10 roll))

(defn bowl [rolls]
  (loop [rolls rolls frame 0 frame-scores []]
    (cond (= frame 10) (apply + frame-scores)
          (is-strike? (first rolls)) (recur (drop 1 rolls) (inc frame) (conj frame-scores (score rolls 3)))
          (is-spare? (take 2 rolls)) (recur (drop 2 rolls) (inc frame) (conj frame-scores (score rolls 3)))
          :else (recur (drop 2 rolls) (inc frame) (conj frame-scores (score rolls 2))))))
