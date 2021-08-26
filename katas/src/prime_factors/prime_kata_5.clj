(ns prime-factors.prime-kata-5)

(defn prime-factors [n]
  (loop [n n
         primes []
         divisor 2]
    (cond (= n 1) primes
          (zero? (rem n divisor)) (recur (/ n divisor) (conj primes divisor) divisor)
          :else (recur n primes (inc divisor)))))
