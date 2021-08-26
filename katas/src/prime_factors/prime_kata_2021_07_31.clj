(ns prime-factors.prime-kata-2021-07-31)

(defn primes [n]
		(loop [n      n
									div    2
									primes []]
				(cond (= n 1) primes
						(zero? (rem n div)) (recur (/ n div) div (conj primes div))
						:else (recur n (inc div) primes))))
