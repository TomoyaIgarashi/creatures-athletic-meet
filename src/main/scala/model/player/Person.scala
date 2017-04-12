package model.player

import ability.{Jumpable, Runnable, Swimable}
import model.Animal

/**
  * Created by hideaki on 2017/04/06.
  */
case class Person(val abilityVal: Int, val factor: Tuple3[Double, Double, Double]) extends Animal with Jumpable with Runnable with Swimable {

}
