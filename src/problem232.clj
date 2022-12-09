(ns problem232)

;; https://leetcode.com/problems/implement-queue-using-stacks/

;; Two-stack solution: one to push to and the other to pop from. If the second stack is
;; exhausted and `pop` is called, the reverse of the first stack goes to the second.

(defn stack-push [stack1 stack2 x]
  [(conj stack1 x) stack2])

(defn stack-pop [stack1 stack2]
  (if (empty? stack2)
    (let [rStack (reverse stack1)]
      [[] (rest rStack)])
    [stack1 (rest stack2)]))

(defn stack-peek [stack1 stack2]
  (if (empty? stack2)
    (let [rStack (reverse stack1)]
      [(last rStack) [] rStack])
    [(last stack2) stack1 stack2]))

(defn stack-empty [stack1 stack2]
  (and (empty? stack1) (empty? stack2)))

;; Tests

(def push-1-then-2 (stack-push [1] [] 2))
(assert (= 1 (first (apply stack-peek push-1-then-2))) "peek [2 1]")

(def pop-result (apply stack-pop push-1-then-2))
(assert (= false (apply stack-empty pop-result)) "empty([2])")
