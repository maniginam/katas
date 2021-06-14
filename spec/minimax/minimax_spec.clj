(ns minimax.minimax-spec
  (:require [speclj.core :refer :all]
            [minimax.minimax :refer :all]))


(describe "Minimax"

  (it "is-game-over?"
    (should-not (is-game-over? [0 1 2 3 4 5 6 7 8]))
    (should-not (is-game-over? [0 1 2 3 4 5 6 7 "X"]))
    (should (is-game-over? ["X" "O" "X" "X" "O" "O" "O" "X" "X"]))
    (should (is-game-over? [0 1 2 3 4 5 "X" "X" "X"]))
    (should (is-game-over? ["O" "O" "O" 3 4 5 6 7 "X"])))

  (it "is-win?"
    (should-not (is-win? [0 1 2 3 4 5 6 7 8]))
    (should-not (is-win? [0 1 2 3 4 5 6 7 "X"]))
    (should-not (is-win? ["O" 1 2 3 4 5 6 7 "X"]))
    (should (is-win? ["O" 1 2 3 4 5 "X" "X" "X"]))
    (should (is-win? ["O" 1 2 "O" 4 5 "O" 7 "X"]))
    (should (is-win? ["X" 1 2 3 "X" 5 6 7 "X"]))
    (should (is-win? [0 1 "O" 3 "O" 5 "O" 7 "X"])))

  (it "scores boxes"
    (should= [0] (score-boxes ["X" "O" "X" 3 "O" "O" "O" "X" "X"] 1 0))
    (should= [9 0] (score-boxes ["X" "O" "X" 3 "O" 5 "O" "X" "X"] -1 0))
    (should= [0 -9 -9] (score-boxes ["X" "O" 2 3 "O" 5 "O" "X" "X"] 1 0))
    (should= [9 9 9 0] (score-boxes ["X" "O" 2 3 "O" 5 6 "X" "X"] -1 0))
    (should= [-9 -9 -9 -9 0] (score-boxes ["X" "O" 2 3 "O" 5 6 7 "X"] 1 0))
    (should= [0 7 0 0 7 0] (score-boxes ["X" 1 2 3 "O" 5 6 7 "X"] -1 0))
    (should= [0 0 0 0 0 0 0] (score-boxes ["X" 1 2 3 "O" 5 6 7 8] 1 0))
    (should= [5 5 5 0 5 5 5 5] (score-boxes ["X" 1 2 3 4 5 6 7 8] -1 0))
    )

  (it "finds best box"
    (should= 3 (find-best-box ["X" "O" "X" 3 "O" "O" "O" "X" "X"] 1))
    (should= 5 (find-best-box ["X" "O" "X" 3 "O" 5 "O" "X" "X"] -1))
    (should= 2 (find-best-box ["X" "O" 2 3 "O" 5 "O" "X" "X"] 1))
    (should= 6 (find-best-box ["X" "O" 2 3 "O" 5 6 "X" "X"] -1))
    (should= 7 (find-best-box ["X" "O" 2 3 "O" 5 6 7 "X"] 1))
    (should= 4 (find-best-box ["X" "O" 2 3 4 5 6 7 "X"] -1))
    (should= 3 (find-best-box ["X" "O" 2 3 4 5 6 7 8] 1))
    (should= 4 (find-best-box ["X" 1 2 3 4 5 6 7 8] -1))
    )
  )


; X O 7
; 9 O 8
; 6 X X