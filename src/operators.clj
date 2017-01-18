;;----------------ops-search operators
;;----------------
;;Guard
;;----------------
(def guard
  '{face {:pre (()
                 ()
                 )
          :add (())
          :del (()
                 ())
          :txt ()
          :cmd []
          }
    })

;;----------------
;;Prisoner
;;----------------
(def operations-prisoner
  '{
    move              {
                       :pre ((on pris ?p1)
                              (connects ?p1 ?p2)
                              (watched ?p2 false)
                              (is cell unlocked))
                       :add ((on pris ?p2))
                       :del ((on pris ?p1))
                       :txt (prisoner moved from ?p1 to ?p2)
                       :cmd [move-junction ?p2]
                       }
    unlock            {:pre ((on pris cell)
                              (is cell locked)
                              )
                       :add ((is cell unlocked))
                       :del ((is cell locked))
                       :txt (unlocked cell)
                       :cmd [unlock-cell]
                       }
    get-key           {:pre ((on pris ?junction)
                              (at ?guard ?junction)
                              (has ?guard key)
                              )
                       :add ((has pris key))
                       :del ((has ?guard key))
                       :txt (key found at ?junction)
                       :cmd [get-key]
                       }
    exit              {:pre ((has pris key)
                              (on pris ?junction)
                              (is ?junction exit)
                              (escaped pris false)
                              )
                       :add ((escaped pris true))
                       :del ((escaped pris false))
                       :txt (prisoner escaped)
                       :cmd [exit-prison]                   ;changed from exit to exit-prison. scared of conflicts
                       }
    })

(let [sizes '{small 5, med 7, large 9}
      sp    " "
      qt    "\""
      str-qt   (fn[x] (str " \"" x "\" "))    ; wrap x in quotes
      stack-no (fn[x] (apply str (rest (str x))))   ; strip "s" of stack name
      ]


  (defmatch nlogo-translate-cmd []
            ((unlock-cell)   :=> (str 'exec.unlock-cell))
            ((exit-prison)   :=> (str 'exec.exit))
            ((get-key)   :=> (str 'exec.get-key))
            ((move-junction ?junction) :=> (str 'exec.move-to-junction sp (str-qt (? junction)) ))
            ((blank) :=> ())
            ;( ?_            :=> (ui-out :dbg 'ERROR '(unknown NetLogo cmd)))
            ))