(ns langtons-ant.two-spec
  (:require
    [speclj.core :refer :all]
    [langtons-ant.two :refer :all]))


(describe "Langton's Ant"
  (context "a cell changes color after stepped on"
    (it "[0 0] cell becomes dark"
      (should= [[0 0]] (update-world [] [0 0])))

    (it "[0 1] cell becomes dark"
      (should= [[0 0] [0 1]] (update-world [[0 0]] [0 1])))

    (it "[1 1] cell becomes dark"
      (should= [[0 0] [0 1] [1 1]] (update-world [[0 0] [0 1]] [1 1])))

    (it "[1 0] cell becomes dark its fourth move"
      (should= [[0 0] [0 1] [1 1] [1 0]] (update-world [[0 0] [0 1] [1 1]] [1 0])))

    (it "[0 0] cell becomes LIGHT"
      (should= [[0 1] [1 1] [1 0]] (update-world [[0 0] [0 1] [1 1] [1 0]] [0 0]))))

  (context "knows where he is"
    (it "first move from [0 0]"
      (should= [0 1] (move-to-next-cell [] [0 0] nil)))

    (it "second move rom [0 1]"
      (should= [1 1] (move-to-next-cell [[0 0]] [0 1] [0 0])))

    (it "third move rom [1 1]"
      (should= [1 0] (move-to-next-cell [[0 0] [0 1]] [1 1] [0 1])))

    (it "fourth move rom [1 0]"
      (should= [0 0] (move-to-next-cell [[0 0] [0 1] [1 1]] [1 0] [1 1])))

    (it "fifth move rom [1 0]"
      (should= [0 -1] (move-to-next-cell [[0 0] [0 1] [1 1] [1 0]] [0 0] [1 0])))
    )

  (context "does the ant dance"
    (it "moves 5 times"
      (should= [[0 1] [1 1] [1 0]] (dance! 5))))
  )
