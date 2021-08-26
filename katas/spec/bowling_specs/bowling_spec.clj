(ns bowling-specs.bowling-spec
  (:require [speclj.core :refer :all]
            [bowling.bowling :refer :all]))

(defn roll-rest-of-game [rolls pins]
  (apply (partial conj rolls) (repeat (- 20 (count rolls)) pins)))

(describe "bowling kata"

  (it "bowls gutter game"
    (should= 0 (score (repeat 20 0))))

  (it "bowls 1 pin down each roll"
    (should= 20 (score (repeat 20 1))))

  (it "bowls 1 spare"
    (should= 16 (score (roll-rest-of-game [5 5 3] 0))))

  (it "bowls 1 strike"
    (should= 16 (score (roll-rest-of-game [10 1 2] 0))))

  (it "bowls perfect game"
    (should= 300 (score (repeat 12 10))))

  (it "bowls 1 strike then 0"
    (should= 44 (score (roll-rest-of-game [10 0 10 5 4] 0))))

  (it "bowls 1 spare then 0"
    (should= 10 (score (roll-rest-of-game [5 5 0] 0))))

  (it "bowls spare with first roll 0"
    (should= 15 (score (roll-rest-of-game [0 10 2 1] 0))))

  )