package model.player

import ability.{Jumpable, Swimable}
import model.Fish

/**
  * Created by hideaki on 2017/04/06.
  */
//マグロ
case class Tuna(val abilityVal: Int, val factor: Tuple3[Double, Double, Double]) extends Fish with Jumpable with Swimable {

}
