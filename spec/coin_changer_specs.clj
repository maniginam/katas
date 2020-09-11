(ns coin-changer-specs
  (:require [speclj.core :refer :all]
            [coin-changer :refer :all]))

(describe "Coin Changer"

  (it "Turns Pennies into Fewest Coins"
    (should= [] (change 0))
    (should= [1] (change 1))
    (should= [1 1] (change 2))
    (should= [1 1 1] (change 3))
    (should= [5] (change 5))
    (should= [5 1] (change 6))
    (should= [10] (change 10))
    (should= [10 5 1 1] (change 17))
    (should= [25] (change 25))
    (should= [25 25 10 5 1 1] (change 67))
    ))