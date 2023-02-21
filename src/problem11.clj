(ns problem11)

;; https://leetcode.com/problems/container-with-most-water/description/

;; ---- the 1st variant; see below for the other variant ---- ;;
;; For an efficient solution we need two indeces, one starts from the head
;; and the other starts from the back. In Clojure, for this we use
;; initial list and its reverse copy.

(defn volumes
  "Vector of volumes between baffles. We choose buffles such that
   the maximal volume is always in this sequence."
  [heights]
  (let [reverseHeights (reverse heights)
        n (count heights)]
    (loop [outerVolumes []
           distance (dec n)
           hs heights
           rhs reverseHeights]
      (if (> distance 0)
        (let [hLeft (first hs)
              hRight (first rhs)
              v (* distance (min hLeft hRight))]
          (if (< hLeft hRight)
            (recur (conj outerVolumes v) (dec distance) (rest hs) rhs)
            (recur (conj outerVolumes v) (dec distance) hs (rest rhs))))
        outerVolumes))))

(assert (= (volumes [1 1]) [1]))
(assert (= (volumes [3 1 4 2]) [6 6 1]))

(defn max-area
  [heights]
  (apply max (volumes heights)))

(assert (= (max-area [1 1]) 1))
(assert (= (max-area [1,8,6,2,5,4,8,3,7]) 49))

;; ---- the 2nd variant, more fp-like ---- ;;

(defn max-area-fp
  [heights]
  (let [indexes (fn f [i j]
                  (if (< i j)
                    (if (> (get heights i) (get heights j))
                      (conj (f i (dec j)) {:i i :j j})
                      (conj (f (inc i) j) {:i i :j j}))
                    (list)))
        volume (fn [pair]
                 (let [i (:i pair)
                       j (:j pair)]
                   (* (- j i) (min (get heights i) (get heights j)))))]
  (apply max (map volume (indexes 0 (dec (count heights)))))))

(assert (= (max-area-fp [1 1]) 1))
(assert (= (max-area-fp [1,8,6,2,5,4,8,3,7]) 49))
