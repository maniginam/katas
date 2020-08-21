(ns prime-factors-specs.prime-kata-11-spec
  (:require [speclj.core :refer :all]
            [prime-kata-11 :refer :all]))

(describe "prime-kata attempt 11 8.17.2020 time: 3:58.99"
  (it "finds all prime factors of n"
    (should= [] (prime-factors 1))
    (should= [2] (prime-factors 2))
    (should= [3] (prime-factors 3))
    (should= [2 2] (prime-factors 4))
    (should= [5] (prime-factors 5))
    (should= [2 3] (prime-factors 6))
    (should= [2 2 2] (prime-factors 8))
    (should= [3 3] (prime-factors 9))))
