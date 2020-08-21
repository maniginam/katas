(ns prime-factors-specs.prime-kata-1-spec
  (:require [speclj.core :refer :all]
            [prime-factors.prime-kata-1 :refer :all]))

(describe "practicing prime-kata: attempt 1; 17:40"
  (it "returns all prime factors of n"
    (should= [] (prime-factors 1))
    (should= [2] (prime-factors 2))
    (should= [3] (prime-factors 3))
    (should= [2 2] (prime-factors 4))
    (should= [2 3] (prime-factors 6))
    (should= [2 2 2] (prime-factors 8))
    (should= [3 3] (prime-factors 9))
    ))
