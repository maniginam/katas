(ns coin-changer-specs
  (:require [speclj.core :refer :all]
            [coin-changer :refer :all]))

(describe "Coin Changer:"

  (for [[pennies coins]
        [[0 []]
         [1 [1]]
         [2 [1 1]]
         [3 [1 1 1]]
         [5 [5]]
         [6 [5 1]]
         [10 [10]]
         [17 [10 5 1 1]]
         [25 [25]]
         [94 [25 25 25 10 5 1 1 1 1]]
         ]]
    (it (str (count coins) (if (= 1 (count coins)) " coin for " " coins for ") pennies (if (= 1 pennies) " penny" " pennies"))
      (should= coins (coin-changer pennies)))))