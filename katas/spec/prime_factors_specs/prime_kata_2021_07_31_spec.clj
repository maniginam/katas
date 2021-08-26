(ns prime-factors-specs.prime-kata-2021-07-31-spec
  (:require 
  [speclj.core :refer :all]
  [prime-factors.prime-kata-2021-07-31 :refer :all]
  ))

(describe "Prime Factors"

  (it "prime factors of n"
    (should= [] (primes 1))
    (should= [2] (primes 2))
    (should= [3] (primes 3))
    (should= [2 2] (primes 4))
    (should= [5] (primes 5))
    (should= [2 3] (primes 6))
    (should= [7] (primes 7))
    (should= [2 2 2] (primes 8))
    (should= [3 3] (primes 9))
    (should= [1041427] (primes 1041427))
    (should= [1041427] (primes 210937500000000000000000000N))
    )

  )
