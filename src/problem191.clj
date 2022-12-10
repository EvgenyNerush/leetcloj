(ns problem191)

;; https://leetcode.com/problems/number-of-1-bits/

;; Hamming weight

(defn hamming-weight
  "Number of 1 bits in 32-bit integer n"
  [n]
  (loop [num n
         step 0
         acc 0]
    (if (< step 32)
      (recur (bit-shift-right num 1) (inc step) (+ acc (bit-and num 1)))
      acc)))

(assert (= (hamming-weight 11) 3) "00000000000000000000000000001011")

(assert (= (hamming-weight 128) 1) "00000000000000000000000010000000")

(assert (= (hamming-weight 4294967293) 31) "11111111111111111111111111111101")
