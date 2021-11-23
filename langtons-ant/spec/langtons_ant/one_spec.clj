(ns langtons-ant.one-spec
		(:require [speclj.core :refer :all]
												[langtons-ant.one :refer :all]))

(describe "Langton's Ant"

		(it "starts as a blank world"
				(should= {[0 0] :white [0 1] :white [1 0] :white [1 1] :white}
						(init-world 2)))

		(it "ant starts on white cell"
				(let [world (init-world 9)
										ant   (init-ant)]
						(should= :white (get world [4 4]))
						(should= [4 4] (:location ant))
						(should-not-be-nil (:facing ant))))

		(it "ant turns clockwise on white"
				(should= 1 (:facing (turn! {:location [4 4] :facing 0} {[4 4] :white})))
				(should= 2 (:facing (turn! {:location [4 4] :facing 1} {[4 4] :white})))
				(should= 3 (:facing (turn! {:location [4 4] :facing 2} {[4 4] :white})))
				(should= 0 (:facing (turn! {:location [4 4] :facing 3} {[4 4] :white}))))

		(it "ant turns counter-clockwise on black"
				(should= 3 (:facing (turn! {:location [4 4] :facing 0} {[4 4] :black})))
				(should= 2 (:facing (turn! {:location [4 4] :facing 3} {[4 4] :black})))
				(should= 1 (:facing (turn! {:location [4 4] :facing 2} {[4 4] :black})))
				(should= 0 (:facing (turn! {:location [4 4] :facing 1} {[4 4] :black}))))

		(it "gets new address"
				(should= [4 3] (get-new-address [4 4] 0))
				(should= [5 4] (get-new-address [4 4] 1))
				(should= [4 5] (get-new-address [4 4] 2))
				(should= [3 4] (get-new-address [4 4] 3)))

		(it "swaps color of cell"
				(should= :black (get (swap-color! [4 4] {[4 4] :white}) [4 4]))
				(should= :white (get (swap-color! [4 4] {[4 4] :black}) [4 4])))

		(it "ant moves north from white cell"
				(let [world {[4 4] :white}
										ant   (assoc (init-ant) :facing 3)
										[ant world] (move-ant ant world)]
						(should= 0 (:facing ant))
						(should= [4 3] (:location ant))
						(should= :black (get world [4 4]))
						(should= 1 (:moves ant))))

		(it "ant moves west from black cell"
				(let [world {[4 4] :black}
										ant   (assoc (init-ant) :facing 0)
										[ant world] (move-ant ant world)]
						(should= 3 (:facing ant))
						(should= [3 4] (:location ant))
						(should= :white (get world [4 4]))
						(should= 1 (:moves ant))))

		(it "ant moves twice"
				(let [world {[4 4] :white [4 3] :white}
										ant (assoc (init-ant) :facing 3)
										[ant world] (move-ant ant world)
										[ant world] (move-ant ant world)]
						(should= :black (get world [4 4]))
						(should= :black (get world [4 3]))
						(should= 1 (:facing ant))
						(should= [5 3] (:location ant))
						(should= 2 (:moves ant))))

		)
