(ns problem1)

;; https://leetcode.com/problems/two-sum/

;; N^2/2 steps

(defn two-sum
  "Returns two indeces (i j) such that nums[i] + nums[j] = target,
   i != j, first solution (of multiple, if present)"
  [nums target]
  (loop [xs nums
         i 0]
    (if (seq xs)
      (let [head-xs (first xs)
            tail-xs (rest xs)
            res (loop [ys tail-xs
                       j (inc i)]
                  (if (seq ys)
                      (let [y (first ys)]
                        (if (= (+ head-xs y) target)
                            (list i j)
                            (recur (rest ys) (inc j))))
                      (list)))]
        (if (seq res)
            res
            (recur tail-xs (inc i))))
      (list))))

(assert (= (two-sum '(2 7 11 15) 9) '(0 1)))

(assert (= (two-sum '(3 2 4) 6) '(1 2)))

(assert (= (two-sum '(3 3) 6) '(0 1)))

(assert (= (two-sum '(1 2 3 4 5 6 7) 12) '(4 6)))
