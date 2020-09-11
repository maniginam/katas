(ns coin-changer)

(defn get-coins [pennies coin-value]
  (/ (- pennies (mod pennies coin-value)) coin-value))

(defn coin-changer [pennies]
  (loop [coins {:quarters 0 :dimes 0 :nickels 0 :pennies 0}
         pennies pennies]
    (cond (< pennies 5) (assoc coins :pennies pennies)
          (< pennies 10) (recur (assoc coins :nickels (get-coins pennies 5)) (mod pennies 5))
          (< pennies 25) (recur (assoc coins :dimes (get-coins pennies 10)) (mod pennies 10))
          :else (recur (assoc coins :quarters (get-coins pennies 25)) (mod pennies 25))
          )))