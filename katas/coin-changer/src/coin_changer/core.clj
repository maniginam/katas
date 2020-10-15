(ns coin-changer.core)

(defn coin-changer [pennies]
  (let [values [25 10 5 1]
        cents-per-coin (reductions #(rem %1 %2) pennies values)
        coins (map #(/ %1 %2) cents-per-coin values)]
    (mapcat #(repeat %1 %2) coins values)))