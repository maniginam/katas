(ns bowling-specs.bowling-2-spec
  (:require [speclj.core :refer :all]
            [bowling.bowling-2 :refer :all]))

(defn roll-rest-of-game [rolls pins]
  (apply (partial conj rolls) (repeat (- 20 (count rolls)) pins)))

(defn roll-game [pins]
  (repeat 20 pins))

(describe "bowling kata attempt 2; 8.20.2020"
  (it "bowls gutter game"
    (should= 0 (score (roll-game 0))))

  (it "bowls 1 pin down each frame"
    (should= 20 (score (roll-game 1))))

  (it "bowls 1 spare"
    (should= 16 (score (roll-rest-of-game [5 5 3] 0))))

  (it "bowls 1 strike"
    (should= 18 (score (roll-rest-of-game [10 1 3] 0))))

  (it "bowls perfect game"
    (should= 300 (score (roll-game 10)))))