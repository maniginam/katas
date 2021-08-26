(ns prime-factors-specs.prime-kata-7-spec
    (:require [speclj.core :refer :all]
      [prime-factors.prime-kata-7 :refer :all]))

(describe "prime kata 7 8.14.2020 time: 5:19:42 and mom called and texted in the middle!
          and i found a new test point before 6--must return [n] before (conj primes n)"
          (it "finds all prime factors of n"
              (should= [] (prime-factors 1))
              (should= [2] (prime-factors 2))
              (should= [3] (prime-factors 3))
              (should= [2 2] (prime-factors 4))
              (should= [5] (prime-factors 5))
              (should= [2 3] (prime-factors 6))
              (should= [2 2 2] (prime-factors 8))
              (should= [3 3] (prime-factors 9))))
