(ns prime-factors-specs.prime-kata-6-spec
    (:require [speclj.core :refer :all]
      [prime-factors.prime-kata-6 :refer :all]))

(describe "prime-kata 6 8.14.20 time: 6:07:19"
          (it "finds all prime factors of n"
              (should= [] (prime-factors 1))
              (should= [2] (prime-factors 2))
              (should= [3] (prime-factors 3))
              (should= [2 2] (prime-factors 4))
              (should= [5] (prime-factors 5))
              (should= [2 3] (prime-factors 6))
              (should= [2 2 2] (prime-factors 8))
              (should= [3 3] (prime-factors 9))))
