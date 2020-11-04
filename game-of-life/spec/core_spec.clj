(ns core-spec
  (:require [speclj.core :refer :all]
            [game-of-life :as sut :refer :all]))

(def dead-world #{})
(def lonely-world #{[0 0]})
(def world-of-two #{[0 0] [0 1]})
(def diagonal-world #{[-1 1] [0 0] [1 -1]})
(def L-world #{[0 1] [0 0] [1 0]})
(def block-world #{[0 0] [0 1] [1 1] [1 0]})

(describe "Game of Life"

  (context "evolves a"
    (it "dead world"
      (should= dead-world (evolve dead-world)))

    (it "lonely world"
      (should= dead-world (evolve lonely-world)))

    (it "world of 2"
      (should= dead-world (evolve world-of-two)))

    (it "diagonal world"
           (should= lonely-world (evolve diagonal-world)))

    (it "L world"
      (should= block-world (evolve L-world)))

    (it "block world"
      (should= block-world (evolve block-world)))
    )

  (context "living neighbors of [0 0] in a"
    (it "lonely world"
      (should= 0 (sut/count-live-neighbors lonely-world [0 0])))

    (it "world of two"
      (should= 1 (sut/count-live-neighbors world-of-two [0 0])))
    )

  (context "a cell's"
    (it "neighbors' addresses"
      (should= #{[-1 1] [0 1] [1 1]
                 [-1 0] [1 0]
                 [-1 -1] [0 -1] [1 -1]} (sut/get-neighbors [0 0]))))

  )
