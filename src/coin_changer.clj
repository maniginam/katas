(ns coin-changer)

(defn coin-changer [pennies]
  (let [values [25 10 5 1]
        coins (reductions #(rem %1 %2) pennies values)
        coin-quantities (map #(int (/ %1 %2)) coins values)]
    (mapcat #(repeat %1 %2) coin-quantities values)))
