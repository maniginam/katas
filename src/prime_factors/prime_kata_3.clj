(ns prime-factors.prime-kata-3)

(defn prime-factors [n]
  (loop [n n
         primes []
         divisor 2]
    (if (> n 1)
      (if (zero? (rem n divisor))
        (recur (/ n divisor) (conj primes divisor) divisor)
        (recur n primes (inc divisor)))
      primes)))