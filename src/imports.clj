(defn ui-out [& r]
  (apply println r))

(load "matcher(0.0m)")
(load "ops-search(1b)")
;(load "strips-search-1a")
(load "socket")
(load "operators")
(load "world")
(load "definitions(1c)")
(load "parsing-1a")
(load "wrappers-1a")

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
