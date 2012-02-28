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
  ([c] c)
  ([b c] `(~b ~c))
  ([b c & more]
     `(comb (comb ~b ~c) ~@more)))

(identical? K (comb S K S K))
