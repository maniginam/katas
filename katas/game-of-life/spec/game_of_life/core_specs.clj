(ns game-of-life.core-specs
  (:require [speclj.core :refer :all]
            [game-of-life.core :refer :all]))

(def dead-world #{})
(def lonely-world #{[0 0]})
(def couples-world #{[0 0] [1 0]})
(def diagonal-world #{[-1 -1] [0 0] [1 1]})
(def block-world #{[0 0] [1 0] [0 1] [1 1]})
(def L-world #{[0 0] [0 1] [1 0]})

(describe "The Game of Life:"

  (it "Evolves a Dead World"
    (should= dead-world (evolve dead-world)))

  (it "Evolves a Lonely World"
    (should= dead-world (evolve lonely-world)))

  (it "Evolves a Couple's World"
    (should= dead-world (evolve couples-world)))

  (it "Evolves a Diagonal World"
    (should= lonely-world (evolve diagonal-world)))

  (it "Evolves a Block World"
    (should= block-world (evolve block-world)))

  (it "Evolves an L World"
    (should= block-world (evolve L-world))))

(describe "Gets to Know the Neighbors"

  (it "Gets a Cell's Neighbors"
    (should= #{[-1 1] [0 1] [1 1]
               [-1 0] [1 0]
               [-1 -1] [0 -1] [1 -1]} (get-neighbors [0 0])))

  (it "Counts Live Neighbors"
    (should= 0 (count-neighbors dead-world [0 0]))
    (should= 0 (count-neighbors lonely-world [0 0]))
    (should= 1 (count-neighbors couples-world [0 0]))
    (should= 2 (count-neighbors diagonal-world [0 0]))
    (should= 3 (count-neighbors block-world [0 0]))))
