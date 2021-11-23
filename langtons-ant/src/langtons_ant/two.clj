(ns langtons-ant.two)

(defn update-world [world cell]
  (let [light? (empty? (filter #(= cell %) world))]
    (if light?
      (conj world cell)
      (remove #(= cell %) world))))


(defn east? [m x] (< m x))
(defn west? [m x] (> m x))
(defn north? [n y] (< n y))
(defn south? [n y] (> n y))

(defn move-vertically-after-eastbound [light? [x y]]
  (if light? [x (dec y)] [x (inc y)]))

(defn move-vertically-after-westbound [light? [x y]]
  (if light? [x (inc y)] [x (dec y)]))

(defn move-horizontally-after-northbound [light? [x y]]
  (if light? [(inc x) y] [(dec x) y]))

(defn move-horizontally-after-southbound [light? [x y]]
  (if light? [(dec x) y] [(inc x) y]))

(defn move-to-next-cell [world [x y :as cell] [m n :as prev-cell]]
  (let [light? (empty? (filter #(= cell %) world))]
    (or (when (nil? prev-cell) [x (inc y)])
        (when (east? m x) (move-vertically-after-eastbound light? cell))
        (when (west? m x) (move-vertically-after-westbound light? cell))
        (when (north? n y) (move-horizontally-after-northbound light? cell))
        (when (south? n y) (move-horizontally-after-southbound light? cell)))))

(defn gui-dance! [{:keys [steps dark-cells ant-cell prev-cell] :as dance}]
  (if (zero? steps)
    dance
    (let [next-cell (move-to-next-cell dark-cells ant-cell prev-cell)
           dark-cells (update-world dark-cells ant-cell)]
      (assoc dance :steps (dec steps) :dark-cells dark-cells :ant-cell next-cell :prev-cell ant-cell))))

(defn dance! [num-of-moves]
  (loop [ant-cell  [0 0]
         world     []
         prev-cell nil
         steps     num-of-moves]
    (if (zero? steps)
      world
      (recur (move-to-next-cell world ant-cell prev-cell)
             (update-world world ant-cell)
             ant-cell
             (when steps (dec steps))))))
