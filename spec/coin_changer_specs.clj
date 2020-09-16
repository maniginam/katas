(ns coin-changer-specs
  (:require [speclj.core :refer :all]
            [coin-changer :refer :all]))

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
    (it (str "changes " pennies (if (= 1 pennies) " penny " " pennies to ") (count coins) (if (= (count coins) 1) " coin" " coins"))
      (should= coins (coin-changer pennies)))))