package model.player

import ability.Jumpable
import model.Bird

/**
  * Created by hideaki on 2017/04/06.
  */

//カラス
case class Crow(val abilityVal: Int, val factor: Tuple3[Double, Double, Double]) extends Bird with Jumpable {

}
