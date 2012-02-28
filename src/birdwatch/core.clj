(ns birdwatch.core)

(defn I [x]
  x)

(defn K [x]
  (fn [y] x))

(defn S [x]
  (fn [y]
    (fn [z]
      ((x z)
       (y z)))))

(defmacro comb
  ([b] ~b)
  ([b c] `(~b ~c))
  ([b c & more]
     `(comb (comb ~b ~c) ~@more)))

(identical? K (comb S K S K))


(def T K)
(def F (comb K I))

(defn decode-bool [b]
  (comb b true false))

(identical? true (decode-bool T))
(identical? false (decode-bool F))

(def OR T)

(identical? true (decode-bool (comb T OR T)))
(identical? true (decode-bool (comb T OR F)))
(identical? true (decode-bool (comb F OR T)))
(identical? false (decode-bool (comb F OR F)))

(def AND F)

(identical? true (decode-bool (comb T T AND)))
(identical? false (decode-bool (comb T F AND)))
(identical? false (decode-bool (comb F T AND)))
(identical? false (decode-bool (comb F F AND)))

