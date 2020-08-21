(ns prime-factors-specs.prime-kata-4-spec
  (:require [speclj.core :refer :all]
            [prime-kata-4 :refer :all]))

(describe "prime kata; attempt 4; 8.11.2020; time: 5:10:99"
          (it "finds all prime factors of n"
              (should= [] (prime-factors 1))
              (should= [2] (prime-factors 2))
              (should= [3] (prime-factors 3))
              (should= [2 2] (prime-factors 4))
              (should= [2 3] (prime-factors 6))
              (should= [2 2 2] (prime-factors 8))
              (should= [3 3] (prime-factors 9))))
