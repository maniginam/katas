(ns coin-changer.core-spec
  (:require [speclj.core :refer :all]
            [coin-changer.core :refer :all]))

(describe "Coin Changer:"

  (for [[pennies coins]
        [[0 []]
         [1 [1]]
         [2 [1 1]]
         [5 [5]]
         [6 [5 1]]
         [10 [10]]
         [11 [10 1]]
         [17 [10 5 1 1]]
         [25 [25]]
         [91 [25 25 25 10 5 1]]
         ]]
   (it (str (count coins) " coins for " pennies " pennies")
    (should= coins (coin-changer pennies)))))