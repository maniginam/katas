(ns coin-changer)

(defn change [pennies]
  (loop [coins []
         pennies pennies]
    (cond (zero? pennies) coins
          (>= (- pennies 25) 0) (recur (conj coins 25) (- pennies 25))
          (>= (- pennies 10) 0) (recur (conj coins 10) (- pennies 10))
          (>= (- pennies 5) 0) (recur (conj coins 5) (- pennies 5))
          (>= (- pennies 1) 0) (recur (conj coins 1) (dec pennies)))
    ))