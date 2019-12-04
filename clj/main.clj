(ns main
  (:require  [clojure.string :as str]))

(defn load_data
  "Loads data from path, split by newline"
  [path, splitter]
  (map #(Integer/parseInt %) (str/split (slurp path) splitter)))

(defn fuel
  "Calculates the fuel consumption for weight"
  [weight]
  (- (int (Math/floor (/ weight 3))) 2))

(defn fuel_total
  "Calculates the fuel consumption for weight, including weight of extra fuel"
  [weight]
  (def new_weight (fuel weight))
  (if (< new_weight 8)
    new_weight
    (+ new_weight (fuel_total new_weight))))

(defn step_a [tot, weight]
  (+ tot (fuel weight))) 

(defn step_b [tot, weight]
  (+ tot (fuel_total weight))) 

(defn one_a [path]
  (reduce step_a 0 (load_data path #"\n")))

(defn one_b [path]
  (reduce step_b 0 (load_data path #"\n")))

; Day 2

; (defn init_data
;   "Initializes state and sets noun and verb"
;   [noun, verb]
;   (assoc
;     (assoc (load_data "../02/input" #",") 1 (str noun))
;     2 (str verb)))

; (defn calculate [program, index]
;   (if (= 99 (get program index))
;     (get program 0)
;     (if (= 1 (get program index))
;       (calculate (assoc program
;                         (get program (+ index 3))
;                         (+
;                          (get program (+ index 1))
;                          (get program (+ index 2))
;                          ))
;                  (+ index 4))
;       (if (= 2 (get program index))
;         (calculate (assoc program
;                           (get program (+ index 3))
;                           (*
;                            (get program (+ index 1))
;                            (get program (+ index 2))
;                            ))
;                    (+ index 4))
;         )
;       )
;     )
;   )

; (defn two_a []
;   (calculate (init_data 12 2) 0)
;   )

(defn -main [& args]
  (println (one_a "../01/input")) ; answer should be 3212842
  (println (one_b "../01/input")) ; answer should be 4816402
  ; (println (init_data 12 2))
  ; (println (two_a)) ; answer should be 4816402
  )
