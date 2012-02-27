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

(identical? K (((S K) S) K))
