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
     (on pris A)
     (isa pris prisoner)

     (is A exit)
     (is cell locked)

     (at guard1 j1)
     (isa guard1 guard)
     (has guard1 key)

     (locked door false)
     (on pris cell)
     (escaped pris false)
     })