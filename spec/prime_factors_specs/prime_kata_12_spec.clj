(ns prime-kata-12-spec
  (:require [speclj.core :refer :all]
            [prime-factors.prime-kata-12 :refer :all]))

(describe "prime-kata 12 8.17.2020"
  (it "finds all prime factors of n"
    (should= [] (prime-factors 1))
    (should= [2] (prime-factors 2))
    (should= [3] (prime-factors 3))
    (should= [2 2] (prime-factors 4))
    (should= [5] (prime-factors 5))
    (should= [2 3] (prime-factors 6))
    (should= [2 2 2] (prime-factors 8))
    (should= [3 3] (prime-factors 9))
    (should= [5 5] (prime-factors 25))
    (should= [2 2 5 5] (prime-factors 100))
    (should= [3 3 3 37] (prime-factors 999))))
