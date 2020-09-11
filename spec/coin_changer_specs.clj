(ns coin-changer-specs
  (:require [speclj.core :refer :all]
            [coin-changer :refer :all]))

(describe "Coin Changer"

  (it "Turns Pennies into Fewest Coins"
    (should= {:quarters 0 :dimes 0 :nickels 0 :pennies 0} (old-coin-changer 0))
    (should= {:quarters 0 :dimes 0 :nickels 0 :pennies 1} (old-coin-changer 1))
    (should= {:quarters 0 :dimes 0 :nickels 1 :pennies 0} (old-coin-changer 5))
    (should= {:quarters 0 :dimes 0 :nickels 1 :pennies 1} (old-coin-changer 6))
    (should= {:quarters 0 :dimes 1 :nickels 0 :pennies 0} (old-coin-changer 10))
    (should= {:quarters 0 :dimes 1 :nickels 0 :pennies 1} (old-coin-changer 11))
    (should= {:quarters 0 :dimes 1 :nickels 1 :pennies 2} (old-coin-changer 17))
    (should= {:quarters 0 :dimes 2 :nickels 0 :pennies 0} (old-coin-changer 20))
    (should= {:quarters 1 :dimes 0 :nickels 0 :pennies 0} (old-coin-changer 25))
    (should= {:quarters 3 :dimes 1 :nickels 1 :pennies 4} (old-coin-changer 94))
    ))