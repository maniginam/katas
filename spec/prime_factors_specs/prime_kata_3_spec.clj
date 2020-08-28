(ns prime-factors-specs.prime-kata-3-spec
  (:require [speclj.core :refer :all]
            [prime-factors.prime-kata-3 :refer :all]))

(describe "prime factors kata attempt 3; 8.11.2020; time:  9:04:18"
          (it "finds all factors of n"
              (should= [] (prime-factors 1))
              (should= [2] (prime-factors 2))
              (should= [3] (prime-factors 3))
              (should= [2 2] (prime-factors 4))
              (should= [2 3] (prime-factors 6))
              (should= [2 2 2] (prime-factors 8))
              (should= [3 3] (prime-factors 9))))