(ns prime-factors-specs.prime-kata-spec
  (:require 
  [speclj.core :refer :all]
  [prime-factors.prime-kata :refer :all]))



(describe "Prime Factors"
  (it "finds the prime factors of n"
    (should= [] (prime-factors 1))
    (should= [2] (prime-factors 2))
    (should= [3] (prime-factors 3))
    (should= [2 2] (prime-factors 4))
    (should= [5] (prime-factors 5))
    (should= [2 3] (prime-factors 6))
    (should= [2 2 2] (prime-factors 8))
    (should= [3 3] (prime-factors 9))
    (should= [] (prime-factors 188))
    )
  )