(ns game-of-life.gol-spec
  (:require 
  [speclj.core :refer :all]
  [game-of-life.gol :refer :all]
  ))


(describe "Game of Life"

  (it "an empty world"
    (should= #{} (evolve #{})))

  (it "a world of one"
    (should= #{} (evolve #{[0 0]})))

  (it "a world with a neighbor"
    (should= #{} (evolve #{[0 0] [0 1]})))

  (it "a world in a block"
    (should= #{[0 0] [0 1] [1 0] [1 1]} (evolve #{[0 0] [0 1] [1 0] [1 1]})))

  (it "a diagonal world"
    (should= #{[0 0]} (evolve #{[-1 1] [0 0] [1 -1]})))

  (it "a world as an L"
    (should= #{[0 0] [0 1] [1 0] [1 1]} (evolve #{[0 0] [0 1] [1 0]})))
  )