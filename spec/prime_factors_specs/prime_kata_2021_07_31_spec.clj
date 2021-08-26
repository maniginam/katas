(ns prime-factors-specs.prime-kata-2021-07-31-spec
  (:require 
  [speclj.core :refer :all]
  [prime-factors.prime-kata-2021-07-31 :refer :all]
  ))

(describe "Prime Factors"

  (it "prime factors of n"
    (should= [] (primes 0)))

  )
