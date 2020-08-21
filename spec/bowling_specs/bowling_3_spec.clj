(ns bowling-specs.bowling-3-spec
  (:require [speclj.core :refer :all]
            [bowling.bowling-3 :refer :all]))

(defn roll-full-game [pins]
  (repeat 20 pins))

(defn roll-rest-of-game [rolls pins]
  (apply (partial conj rolls) (repeat (- 20 (count rolls)) pins)))

(describe "bowling kata attempt 3; 8.21.2020; time: 14:36 to complete unrefactored"
  (it "bowls gutter game"
    (should= 0 (score (roll-full-game 0))))

  (it "bowls 1 pin down each roll"
    (should= 20 (score (roll-full-game 1))))

  (it "bowls 1 spare"
    (should= 16 (score (roll-rest-of-game [5 5 3] 0))))

  (it "bowls 1 strike"
    (should= 26 (score (roll-rest-of-game [10 5 3] 0))))

  (it "bowls perfect game"
    (should= 300 (score (roll-full-game 10))))


  )