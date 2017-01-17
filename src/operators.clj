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
    move-to-junction  {:pre ((moving prisoner ?corridor)
                              (connects ?corridor ?junction)
                              )
                       :add ((on prisoner ?junction))
                       :del ((moving prisoner ?corridor))
                       :txt (prisoner moved from ?corridor to ?junction)
                       :cmd [move-junction ?junction]
                       }
    move-to-corridoor {:pre ((on prisoner ?junction)
                              (connects ?junction ?corridor)
                              (watched ?corridor false)
                              )
                       :add ((moving prisoner ?corridor))
                       :del ((on prisoner ?junction))
                       :txt (prisoner moved from ?junction to ?corridor)
                       :cmd [blank]
                       }
    unlock            {:pre ((at prisoner c)
                              (is c locked)
                              )
                       :add ((is c unlocked))
                       :del ((is c locked))
                       :txt (unlocked cell)
                       :cmd [unlock-cell]
                       }
    leave-cell        {:pre ((at prisoner c)
                              (is c unlocked)
                              (connects c ?junction)
                              )
                       :add ((on prisoner ?junction))
                       :del ((at prisoner c))
                       :txt (leave cell)
                       :cmd [move-junction ?junction]
                       }
    get-key           {:pre ((on prisoner ?junction)
                              (at ?guard ?junction)
                              (has ?guard key)
                              )
                       :add ((has prisoner key))
                       :del ((has ?guard key))
                       :txt (key twoked)
                       :cmd [get-key]
                       }
    exit              {:pre ((has prisoner key)
                              (on prisoner ?junction)
                              (is ?junction exit)
                              (escaped prisoner false)
                              )
                       :add ((escaped prisoner true))
                       :del ((escaped prisoner false))
                       :txt (get ops-searched m8)
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