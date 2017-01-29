

;======================================
; general settings
;======================================

(def settings
  {
    :search-type :breadth-first
    ;:search-type :strips

   :world-type world
   ;:world-type nlogoworld
   })


;======================================
; morphology rules
;======================================

; std rules

(def sentence-morph-rules
  ; NB: these rules work on a single pass basis so
  ;     you MUST prioritize rules in the order you
  ;     want them applied

  '( ((??a and then ??b)  => (??a stop ??b))
     ((??a and ??b)       => (??a stop ??b))
     ((??a then ??b)      => (??a stop ??b))
     ((??a now ??b)       => (??a stop ??b))
     ))


(def word-match-rules
  ; NB: these rules work on a single pass basis so
  ;     you MUST prioritize rules in the order you
  ;     want them applied

  '(
     ))



;====================================================
; mapping for resolved parses to search goals, etc
;====================================================

(declare
  goal apply-exec
  extract-to-map clear-block-data
  it-reference)

(def default-block-spec '{color grey, size med, shape cube})


(defmatch process-cmd []
  ;((grasp ?x)        :=> (set-atom! it-reference (? x))   (goal (mout '(holds ?x))))
  ;((put-on ?x ?y)    :=> (set-atom! it-reference (? x))   (goal (mout '(on ?x ?y))))
  ;((put-at ?x ?s)    :=> (set-atom! it-reference (? x))   (goal (mout '(at ?x ?s))))
  ;((move-hand-to ?s) :=> (apply-exec ('puton exec-ops) (mout '{s ?s})))
          ((move-to ?x ?y) :=> (set-atom! it-reference (? x)) (goal (mout '(on ?x ?y))))
          ((exit-prison) :=> (goal (mout '(escaped prisoner true))))
          ((get-key ?x) :=> (goal (mout '(has prisoner ?x))))
          ((unlock ?x) :=> (goal (mout '(unlocked ?x))))
  ;((create ?x ?spec) :=>
  ;  (goal '(hand empty))
  ;  (set-atom! it-reference (? x))
  ;  (apply-exec ('create exec-ops)
  ;    (conj {'x (? x)}
  ;      (merge default-block-spec
  ;        (extract-to-map (? spec))))))
  ;((destroy ?x)      :=>
  ;  (goal (mout '(holds ?x)))
  ;  (apply-exec ('dispose exec-ops))
  ;  (set-atom! it-reference false))
  ;((reset)         :=> (clear-block-data) (nlogo-send "setup"))
  ; ( ?x             :=> (ui-out :dbg 'ERROR '(unknown NetLogo request) (? x)))
  )



;================================
; Netlogo comms & filters
;================================


;(let [sizes '{small 5, med 7, large 9}
;      sp    " "
;      qt    "\""
;      str-qt   (fn[x] (str " \"" x "\" "))    ; wrap x in quotes
;      stack-no (fn[x] (apply str (rest (str x))))   ; strip "s" of stack name
;      ]


  ;(defmatch nlogo-translate-cmd []
  ;  ((make ?nam ?obj ?size ?color)
  ;                  :=> (str 'exec.make (str-qt (? nam)) (str-qt (? obj))
  ;                                      ((? size) sizes) (str-qt (? color))))
  ;  ((move-to ?s)   :=> (str 'exec.move-to sp (stack-no (? s))))
  ;  ((drop-at ?s)   :=> (str 'exec.drop-at sp (stack-no (? s))))
  ;  ((pick-from ?s) :=> (str 'exec.pick-from sp (stack-no (? s))))
  ;  ( ?_            :=> (ui-out :dbg 'ERROR '(unknown NetLogo cmd)))
  ;  ))






