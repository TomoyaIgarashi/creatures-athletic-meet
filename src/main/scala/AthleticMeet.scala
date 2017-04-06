import model.Creature
import model.player._

/**
  * Created by hideaki on 2017/04/06.
  */
object AthleticMeet {


  def main(args: Array[String]): Unit = {
    val players = getPlayers()

    val runRank = raceRunning(players)
    val swimRank = raceSwimming(players)
    val flyRank = raceFlying(players)

    val result = aggregatePoints(players, runRank, swimRank, flyRank)
    result.zipWithIndex.foreach { case (creature, rank) =>
      println(s"${rank + 1}: ${creature}")
    }
  }

  def getPlayers(): Seq[Creature] = {
    List(
      new Person(7),
      new Cheetah(5),
      new Crow(1),
      new Ostrich(1),
      new Tuna(4),
      new Sanma(7)
    )
  }
  def getRandom: Int ={
    scala.math.floor(scala.math.random() * 10).toInt + 1
  }

  //TODO 走れる動物のみを受け付けるように引数を変更
  def raceRunning(list: Seq[Creature]): Seq[Creature] = {
    list
  }

  //TODO 泳げる動物のみを受け付けるように引数を変更
  def raceSwimming(list: Seq[Creature]): Seq[Creature] = {
    List()
  }

  //TODO 飛べる(ジャンプできる)動物のみを受け付けるように引数を変更
  def raceFlying(list: Seq[Creature]): Seq[Creature] = {
    List()
  }

  val points = List(10, 5, 3, 2, 1, 0) //順位に応じたポイント

  def aggregatePoints(players: Seq[Creature], runRank: Seq[Creature], swimRank: Seq[Creature], flyRank: Seq[Creature]): Seq[(Int, Creature)] = {
    players.map(creature => {
      val rankIndex = runRank.indexOf(creature)
      val point = if (rankIndex >= 0) points(rankIndex) else 0
      (point, creature)
    }).sortBy(tup => tup._1 * -1)
  }

}
