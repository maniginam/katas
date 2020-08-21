(ns prime-factors.prime-kata-1)

(defn prime-factors [n]
  (loop [primes []
         n n
         divisor 2]
    (if (> n 1)
      (if (zero? (rem n divisor))
        (recur (conj primes divisor) (/ n divisor) divisor)
        (recur primes n (inc divisor)))
      primes)))
