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
                       :pre ((on prisoner ?p1)
                              (connects ?p1 ?p2)
                              (watched ?p2 false)
                              (unlocked cell))
                       :add ((on prisoner ?p2))
                       :del ((on prisoner ?p1))
                       :txt (prisoner moved from ?p1 to ?p2)
                       :cmd [move-junction ?p2]
                       }
    unlock            {:pre ((on prisoner cell)
                              (locked cell)
                              )
                       :add ((unlocked cell))
                       :del ((locked cell))
                       :txt (unlocked cell)
                       :cmd [unlock-cell]
                       }
    get-key           {:pre ((on prisoner ?junction)
                              (at ?guard ?junction)
                              (has ?guard key)
                              )
                       :add ((has prisoner key))
                       :del ((has ?guard key))
                       :txt (key found at ?junction)
                       :cmd [get-key]
                       }
    exit              {:pre ((has prisoner key)
                              (on prisoner ?junction)
                              (isa ?junction exit)
                              (escaped prisoner false)
                              )
                       :add ((escaped prisoner true))
                       :del ((escaped prisoner false))
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