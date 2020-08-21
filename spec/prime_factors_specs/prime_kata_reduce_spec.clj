;(ns prime-kata-reduce-spec
;    (:require [speclj.core :refer :all]
;      [prime-kata-reduce :refer :all]))
;
;(describe "prime kata using reduce; 8.15.2020"
;          (it "finds all prime factors of n"
;              (should=[] (prime-factors 1))
;              (should=[2] (prime-factors 2))
;              (should=[3] (prime-factors 3))
;              (should=[2 2] (prime-factors 4))
;              (should=[5] (prime-factors 5))
;              (should=[2 3] (prime-factors 6))))
