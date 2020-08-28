(ns prime-kata-2-spec
  (:require [speclj.core :refer :all]
            [prime-factors.prime-kata-2 :refer :all]))

(describe "prime-kata attempt 2; 8.11.2020; total time: 10:42:18"
          (it "finds all prime factors of n"
              (should= [] (prime-factors 1))
              (should= [2] (prime-factors 2))
              (should= [3] (prime-factors 3))
              (should= [2 2] (prime-factors 4))
              (should= [5] (prime-factors 5))
              (should= [2 3] (prime-factors 6))
              (should= [2 2 2] (prime-factors 8))
              (should= [3 3] (prime-factors 9))))
