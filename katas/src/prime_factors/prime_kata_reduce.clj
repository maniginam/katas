(ns prime-factors.prime-kata-reduce)

(defn prime-factors [n]
      (let [numbers (range 2 (inc n))]
           (cond (= n 1) []
                 (<= n 3) [n]
                 :else (let [primes []
                             divisor 2
                              factors (reverse (filter #(zero? (rem n %)) numbers))]
                            (map #(if (> % divisor) (reduce / factors) divisor) factors)))))

