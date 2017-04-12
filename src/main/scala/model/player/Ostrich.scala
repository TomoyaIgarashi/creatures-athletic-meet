package model.player

import ability.{Jumpable, Runnable}
import model.Bird

/**
  * Created by hideaki on 2017/04/06.
  */
//ダチョウ
case class Ostrich(val abilityVal: Int, val factor: Tuple3[Double, Double, Double]) extends Bird with Jumpable with Runnable {

}
