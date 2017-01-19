;;----------------
;;World Define
;;----------------
(def world
  '#{
     (connects A B) (connects B A) (watched A false) (isa A location)
     (connects B C) (connects C B) (watched B false) (isa B location)
     (connects C E) (connects E C) (watched C false) (isa C location)
     (connects E H) (connects H E) (watched D false) (isa D location)
     (connects H G) (connects G H) (watched E false) (isa E location)
     (connects G F) (connects F G) (watched F false) (isa F location)
     (connects F D) (connects D F) (watched G false) (isa G location)
     (connects D A) (connects A D) (watched H false) (isa H location)
     (connects cell H) (connects H cell)
     (on pris cell)
     (isa pris prisoner)

     (isa A exit)
     (is cell locked)

     (at guard1 B)
     (isa guard1 guard)
     (has guard1 key)

     (locked door false)
     (escaped pris false)
     })

(def nlogoworld
  '#{
     (connects j7 c1) (connects c1 j7) (watched c1 false) (isa c1 location)
     (connects j7 c2) (connects c2 j7) (watched c2 false) (isa c2 location)
     (connects c1 j2) (connects j2 c1) (watched c3 false) (isa c3 location)
     (connects c2 j1) (connects j1 c2) (watched c4 true) (isa c4 location)
     (connects j1 c4) (connects c4 j1) (watched c5 true) (isa c5 location)
     (connects c4 j3) (connects j3 c4) (watched c6 false) (isa c6 location)
     (connects c3 j2) (connects j2 c3) (watched c7 false) (isa c7 location)
     (connects c3 j3) (connects j3 c3) (watched c8 false) (isa c8 location)
     (connects j3 c5) (connects c5 j3) (watched j1 false) (isa j1 location)
     (connects j3 c6) (connects c6 j3) (watched j2 false) (isa j2 location)
     (connects c5 j4) (connects j4 c5) (watched j3 false) (isa j3 location)
     (connects j4 c7) (connects c7 j4) (watched j4 false) (isa j4 location)
     (connects c7 j6) (connects j6 c7) (watched j5 false) (isa j5 location)
     (connects j6 c8) (connects c8 j6) (watched j6 false) (isa j6 location)
     (connects c8 j5) (connects j5 c8) (watched j7 false) (isa j7 location)
     (connects j5 c6) (connects c6 j5)
     (connects cell j6) (connects j6 cell)
     (on pris cell)
     (isa pris prisoner)

     (isa j7 exit)
     (is cell locked)

     (at guard1 j1)
     (isa guard1 guard)
     (has guard1 key)

     (locked door false)
     (escaped pris false)
     }
  )