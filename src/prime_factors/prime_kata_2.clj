(ns prime-factors.prime-kata-2)


(defn prime-factors [n]
  (loop [n n
         primes []
         divisor 2]
    (if (= n 1)
      primes
      (if (zero? (rem n divisor))
        (recur (/ n divisor) (conj primes divisor) divisor)
        (recur n primes (inc divisor))))))