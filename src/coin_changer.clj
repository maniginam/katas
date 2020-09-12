(ns coin-changer)

(defn coin-changer [pennies]
  (let [values [25 10 5 1]
        cents-per-coins (reductions #(rem %1 %2) pennies values)
        coins (map #(int (/ %1 %2)) cents-per-coins values)]
    (mapcat #(repeat %1 %2) coins values)))
