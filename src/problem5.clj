(ns problem5)

;; https://leetcode.com/problems/longest-palindromic-substring/

;; Search until the letters at the left and at the right are the same

(defn half-palindrome
  "Compares lhs and rhs letter-by-letter, put letters to res while they are the same."
   [lhs rhs res]
  (if (seq lhs)
    (if (seq rhs)
      (let [x (first lhs)
            y (first rhs)]
        (if (= x y)
         (half-palindrome (rest lhs) (rest rhs) (conj res x))
          res))
      res)
    res))

(defn go
  "The loop over the list of chars: splits it to lhs and rhs, then searches for a
   palindrome."
  [lhs rhs res]
  (if (seq rhs)
    (let [x (first rhs)
          xs (rest rhs)
          half-p-with-pivot (half-palindrome lhs xs (list x))
          half-p-no-pivot (half-palindrome lhs rhs (list))
          size-with-pivot (- (* 2 (count half-p-with-pivot)) 1)
          size-no-pivot (* 2 (count half-p-no-pivot))
          size-res (count res)]
      (if (> size-with-pivot size-no-pivot)
        (if (> size-with-pivot size-res)
          (go (conj lhs x) xs (concat half-p-with-pivot (rest (reverse half-p-with-pivot))))
          (go (conj lhs x) xs res))
        (if (> size-no-pivot size-res)
          (go (conj lhs x) xs (concat half-p-no-pivot (reverse half-p-no-pivot)))
          (go (conj lhs x) xs res))
        ))
    res))

(defn longestPalindrome
  [string]
  (let [res (go (list) (seq string) (list))]
    (apply str res)))

(assert (= (half-palindrome '(\a \b \c) '(\a \b) (list)) '(\b \a)))

(assert (= (longestPalindrome "babd") "bab"))

(assert (= (longestPalindrome "cbbd") "bb"))

(assert (= (longestPalindrome "abbaxyzczyxyzczyxyz") "xyzczyxyzczyx"))
