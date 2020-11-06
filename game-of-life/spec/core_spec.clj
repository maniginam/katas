(ns core-spec
  (:require [speclj.core :refer :all]
            [game-of-life :as sut :refer :all]))

(def dead-world #{})
(def lonely-world #{[0 0]})
(def world-of-two #{[0 0] [1 0]})
(def diagonal-world #{[-1 1] [0 0] [1 -1]})
(def L-world #{[0 1] [0 0] [1 0]})
(def block-world #{[0 0] [0 1] [1 1] [1 0]})

  (describe "Game of Life"

    (context "evolves"
      (it "a dead world"
        (should= dead-world (evolve dead-world)))

      (it "a lonely world"
        (should= dead-world (evolve lonely-world)))

      (it "a world of two"
        (should= dead-world (evolve world-of-two)))

      (it "a diagonal world"
        (should= lonely-world (evolve diagonal-world)))

      (it "a swapping line world"
        (should= #{[0 0] [0 -1] [0 1]} (evolve #{[-1 0] [0 0] [1 0]}))
        (should= #{[-1 0] [0 0] [1 0]} (evolve #{[0 -1] [0 0] [0 1]})))

      (it "an L world"
        (should= block-world (evolve L-world)))
      )

    (context "neighbors"
      (it "count"
        (should= 0 (count-neighbors lonely-world [0 0])))
      )
    ;(it "TRIAL"
    ;  (should= #{[9 0] [8 0] [7 0] [6 0] [5 0] [4 0] [3 0] [2 0] [1 0] [0 0] [-1 0] [-2 0] [-3 0] [-4 0] [-5 0] [-6 0] [-7 0] [-8 0] [-9 0]}
    ;           (evolve #{[10 0] [9 0] [8 0] [7 0] [6 0] [5 0] [4 0] [3 0] [2 0] [1 0] [0 0] [-1 0] [-2 0] [-3 0] [-4 0] [-5 0] [-6 0] [-7 0] [-8 0] [-9 0] [-10 0]})))

    )