/**
  * Created by tomoya.igarashi on 2017/04/06.
  */

import org.scalatest.{DiagrammedAssertions, FunSuite}
import ability._
import model.player._

class AthleticMeetSuite extends FunSuite with DiagrammedAssertions {
  val rnd = new java.util.Random(1L)
  val xs = for {
    _ <- 0 to 5
  } yield {
    rnd.nextInt(20) + 1
  }
  val players = AthleticMeet.getPlayers(xs)

  val raceRunning = AthleticMeet.race(players) {
    case v: Runnable => (v, v.abilityVal * v.factor._1)
  }
  val raceSwimming = AthleticMeet.race(players) {
    case v: Swimable => (v, v.abilityVal * v.factor._2)
  }
  val raceFlying = AthleticMeet.race(players) {
    case v: Jumpable => (v, v.abilityVal * v.factor._3)
  }
  val aggregatePoints = AthleticMeet.aggregatePoints(players, raceRunning, raceSwimming, raceFlying)
  val averagePoints = AthleticMeet.averagePoints(aggregatePoints)

  test("AthleticMeet#getPlayers") {
    val result = List(
      Person(6, (1.0, 1.0, 1.0)),
      Cheetah(9, (5.0, 0.5, 1.5)),
      Crow(8, (0.2, 0.0, 3.0)),
      Ostrich(14, (10.0, 0.0, 1.0)),
      Tuna(15, (0.0, 5.0, 0.3)),
      Sanma(5, (0.0, 3.0, 0.1)))
    assertResult(result) {
      players
    }
  }

  test("AthleticMeet#race. Runnalbe only") {
    val result = List(
      (Ostrich(14, (10.0, 0.0, 1.0)), 10),
      (Cheetah(9, (5.0, 0.5, 1.5)), 5),
      (Person(6, (1.0, 1.0, 1.0)), 3))
    assertResult(result) {
      raceRunning
    }
  }

  test("AthleticMeet#race. Swimable only") {
    val result = List(
      (Tuna(15, (0.0, 5.0, 0.3)), 10),
      (Sanma(5, (0.0, 3.0, 0.1)), 5),
      (Person(6, (1.0, 1.0, 1.0)), 3),
      (Cheetah(9, (5.0, 0.5, 1.5)), 2))
    assertResult(result) {
      raceSwimming
    }
  }

  test("AthleticMeet#race. Jumpable only") {
    val result = List(
      (Crow(8, (0.2, 0.0, 3.0)), 10),
      (Ostrich(14, (10.0, 0.0, 1.0)), 5),
      (Cheetah(9, (5.0, 0.5, 1.5)), 3),
      (Person(6, (1.0, 1.0, 1.0)), 2),
      (Tuna(15, (0.0, 5.0, 0.3)), 1),
      (Sanma(5, (0.0, 3.0, 0.1)), 0))
    assertResult(result) {
      raceFlying
    }
  }

  test("AthleticMeet#aggregatePoints") {
    val result = List(
      (15, Ostrich(14, (10.0, 0.0, 1.0))),
      (11, Tuna(15, (0.0, 5.0, 0.3))),
      (10, Cheetah(9, (5.0, 0.5, 1.5))),
      (10, Crow(8, (0.2, 0.0, 3.0))),
      (8, Person(6, (1.0, 1.0, 1.0))),
      (5, Sanma(5, (0.0, 3.0, 0.1))))
    assertResult(result) {
      aggregatePoints
    }
  }

  test("AthleticMeet#averagePoints") {
    val result = List(9.0, 8.0, 12.5)
    assertResult(result) {
      averagePoints
    }
  }
}
