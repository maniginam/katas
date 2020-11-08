(ns game-of-life-specs
  (:require [speclj.core :refer :all]
            [game-of-life :refer :all]))

(def dead-world #{})
(def lonely-world #{[0 0]})
(def world-of-two #{[0 0] [1 0]})
(def diagonal-world #{[-1 1] [0 0] [1 -1]})
(def block-world #{[0 0] [0 1] [1 1] [1 0]})
(def L-world #{[0 0] [0 1] [1 0]})

(describe "Game of Life:"

  (context "evolves"
    (it "a dead wolrd"
      (should= dead-world (evolve dead-world)))

    (it "a lonely world"
      (should= dead-world (evolve lonely-world)))

    (it "a world of two"
      (should= dead-world (evolve world-of-two)))

    (it "a diagonal world"
      (should= lonely-world (evolve diagonal-world)))

    (it "a block-world"
      (should= block-world (evolve block-world)))

    (it "an L-world"
      (should= block-world (evolve L-world))))

  (context "counts living neighbors in"
    (it "a lonely world"
      (should= 0 (count-neighbors lonely-world [0 0])))

    (it "a world of two"
      (should= 1 (count-neighbors world-of-two [0 0])))

    (it "a diagonal world"
      (should= 2 (count-neighbors diagonal-world [0 0]))))
  )