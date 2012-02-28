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

