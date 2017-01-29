(defn ui-out [& r]
  (apply println r))

(load "libs/matcher(0.0m)")
(load "libs/ops-search(1b)")
(load "libs/socket")
(load "operators")
(load "world")
(load "libs/definitions(1c)")
(load "libs/parsing-1a")
(load "libs/wrappers-1a")
(load "parser")

(defn startup [port]
  (set-shrdlu-comms port)
  )

(defn ui-out [& r]
  (apply println r))

(defn wait-for-input []
    (while true
      (do
        (Thread/sleep 1000)
        (shrep-1 (reverse (map symbol (into () (str/split (nlogo-read) #" ")))))
        ))
  )

;;Examples of sentences to parse

;;(shrep-1 '(move the pris to H))
