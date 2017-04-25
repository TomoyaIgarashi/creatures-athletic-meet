import ability._
import model._
import model.player._

/**
  * Created by hideaki on 2017/04/06.
  */
object AthleticMeet {

  type -->[A, B] = PartialFunction[A, B]

  def main(args: Array[String]): Unit = {
    val xs = for {
      _ <- 0 to 5
    } yield {
      getRandom
    }
    val players = getPlayers(xs)

    val runRank = race(players) {
      case v: Runnable => (v, v.abilityVal * v.factor._1)
    }
    val swimRank = race(players) {
      case v: Swimable => (v, v.abilityVal * v.factor._2)
    }
    val flyRank = race(players) {
      case v: Jumpable => (v, v.abilityVal * v.factor._3)
    }

    val rank = aggregatePoints(players, runRank, swimRank, flyRank)
    rank.zipWithIndex.foreach { case (creature, rank) =>
      println(s"${rank + 1}: ${creature._1}point: ${creature._2}")
    }
    val average = averagePoints(rank)
    average.foreach(println)
  }

  def getPlayers(abilities: Seq[Int]): Seq[Creature] = {
    require(abilities.length == 6)
    List(
      new Person(abilities(0), (1, 1, 1)),
      new Cheetah(abilities(1), (5, 0.5, 1.5)),
      new Crow(abilities(2), (0.2, 0, 3)),
      new Ostrich(abilities(3), (10, 0, 1)),
      new Tuna(abilities(4), (0, 5, 0.3)),
      new Sanma(abilities(5), (0, 3, 0.1))
    )
  }

  def getRandom: Int = {
    scala.math.floor(scala.math.random() * 10).toInt + 1
  }

  val points = List(10, 5, 3, 2, 1, 0) //順位に応じたポイント

  def race(creatures: Seq[Creature])(pf: Creature --> (Creature, Double)): Seq[(Creature, Int)] = {
    val xs = creatures collect pf sortBy (_._2 * -1) map (_._1)
    for {
      withIndex <- xs.zipWithIndex
    } yield {
      val point = points.lift(withIndex._2).getOrElse(0)
      (withIndex._1, point)
    }
  }

  def aggregatePoints(players: Seq[Creature],
                      runRank: Seq[(Creature, Int)],
                      swimRank: Seq[(Creature, Int)],
                      jumpRank: Seq[(Creature, Int)]): Seq[(Int, Creature)] = {
    def getPoint(creature: Creature, ranks: Seq[(Creature, Int)]): Int = {
      (for {
        rank <- ranks if rank._1 == creature
      } yield {
        rank._2
      }).lift(0).getOrElse(0)
    }

    (for {
      player <- players
    } yield {
      val runPoint = getPoint(player, runRank)
      val swimPoint = getPoint(player, swimRank)
      val jumpPoint = getPoint(player, jumpRank)
      val point = runPoint + swimPoint + jumpPoint
      (point, player)
    }).sortBy(tup => tup._1 * -1)
  }

  def ranks(points: Seq[(Int, Creature)]): Seq[Int] = {
    val xs = points.map(t => t._1)
    val ys = xs.distinct
    val zs = points.map { p =>
      ys.count(_ > p._1) + 1
    }

    return zs
  }

  def averagePoints(playerPoints: Seq[(Int, Creature)]): Seq[Double] = {
    def getAverage(pf: (Int, Creature) --> Int): Double = {
      val xs = playerPoints collect pf
      xs.sum / xs.length.toDouble
    }

    val animalAverage = getAverage {
      case (n: Int, _: Animal) => n
    }
    val fishAverage = getAverage {
      case (n: Int, _: Fish) => n
    }
    val birdAverage = getAverage {
      case (n: Int, _: Bird) => n
    }
    Seq(animalAverage, fishAverage, birdAverage)
  }
}
