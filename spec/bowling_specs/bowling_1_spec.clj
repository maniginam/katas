(ns bowling-specs.bowling-1-spec
  (:require [speclj.core :refer :all]
            [bowling.bowling-1 :refer :all]))

(defn roll-many [n pins]
  (repeat n pins))

(describe "bowling kata attempt 1; 8.19.20"
  (it "bowls gutter game"
    (should= 0 (bowl (roll-many 20 0))))

  (it "bowls 1 pin knocked each roll"
    (should= 20 (bowl (roll-many 20 1))))

  (it "bowls 1 spare"
    (let [rolls (roll [5 5 3] (roll-many 17 0))]
      (should= 16 (bowl rolls))))

  (it "bowls 1 strike"
    (let [rolls (roll [10 5 3] (roll-many 16 0))]
      (should= 26 (bowl rolls))))

  (it "plays perfect game"
    (should= 300 (bowl (roll-many 12 10))))

)
