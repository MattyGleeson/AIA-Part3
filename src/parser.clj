;==========================================
; lexicon
;==========================================

(def lexicon
  '{
    guard {:cat noun, :sem (isa ?p guard)}
    prisoner  {:cat noun, :sem (isa ?x prisoner)}
    pris {:cat noun, :sem (isa ?x prisoner)}
    convict {:cat noun, :sem (isa ?x prisoner)}

    prison {:cat noun}
    location  {:cat noun, :sem (isa ?x location)}
    key       {:cat noun, :sem (isa ?x k)}
    cell      {:cat noun, :sem (isa ?x cell)}

    the    {:cat det} ;, :sem undef}
    a      {:cat det} ;, :sem undef}
    an     {:cat det}
    any    {:cat det}

    grasp   {:cat verb1, :arity 1, :sem get-key}
    find    {:cat verb1, :arity 1, :sem get-key}
    get     {:cat verb1, :arity 1, :sem get-key}
    unlock  {:cat verb1, :arity 1, :sem unlock}

    ;make    {:cat make, :arity 1, :sem make}
    ;create  {:cat make, :arity 1, :sem make}

    place   {:cat put2, :arity 2, :sem move-to}
    move    {:cat put2, :arity 2, :sem move-to}
    put     {:cat put2, :arity 2, :sem move-to}

    exit    {:cat exit, :sem exit-prison}
    leave {:cat exit, :sem exit-prison}
    escape {:cat exit, :sem exit-prison}

    })

;___ noun group ___________________________

(defmatch noun-group []
          (((-> ?d det?) (-> ?n noun?))       ; NG -> Det N
            :=> {:cat  'ng
                 :id   '?x,    ; by default
                 :type 'tuples
                 :sem  (list (? n))
                 }
            )
          (((-> ?d det?) (-> ??a adjG) (-> ?n noun?))    ; NG -> Det AdjG N
            :=> {:cat  'ng
                 :id   '?x,    ; by default
                 :type 'tuples
                 :sem  (mout '(??a ?n))
                 }
            ))



;___ adj group ___________________________

(defn adjG [lis]                   ; AdjG -> *Adj
  (and (every? #(adj? %) lis)
       (map #(adj? %) lis)
       ))


;___ noun phrase ___________________________

(defmatch noun-phrase []
          (((-> ??np noun-group)) :=> (assoc (? np) :cat 'np))   ; NP -> NG
          (((-> ?obj location?))                                    ; NP -> block
            :=> {:cat 'np, :type 'id :sem  (? obj)}
            )
          (((-> ?obj #(= % 'it)))                 ; NP -> block
            :=> {:cat 'np, :type 'id :sem @it-reference}
            )
          (((-> ??np noun-group) (-> ??pp prep-phrase))          ; NP -> NG PP
            :=> (let [sym (gensym 'x)]
                  {:id   '?x
                   :cat  'np
                   :type 'tuples
                   :sem  (concat (:sem (? np)) (:sem (? pp)))
                   }))
          )



;___ prep phrase ___________________________

(defmatch prep-phrase []
          (((-> ?prep prep?) (-> ??np noun-phrase))             ; PP -> Prep NP
            :=> (pnp-sems (? prep) (? np))
            ))



;___ cmd phrase ___________________________
; this is the top level phrase

(defmatch parse []
          (((-> ?cmd verb1?) (-> ??obj noun-group))
            :=> (list (? cmd) (? obj))
            )
          ;(((-> ?cmd make?) (-> ??obj noun-group))
          ;  :=> (let [obj (? obj)
          ;            id  (gen-block-name)
          ;            ]
          ;        (list 'create id
          ;              (edit (:id obj) id (:sem obj))))
          ;  )

          (((-> ?cmd put2?) (-> ??obj noun-group) to (-> ?s location?))
            :=> (list 'move-to (? obj) (? s))
            )

          (((-> ?cmd put2?) (-> ??obj noun-group) on (-> ?s location?))
            :=> (list 'move-to (? obj) (? s))
            )

          (((-> ?cmd exit?))
            :=> (list (? cmd))
            )

          (((-> ?cmd exit?) (-> ??obj noun-group))
            :=> (list (? cmd))
            )


          )