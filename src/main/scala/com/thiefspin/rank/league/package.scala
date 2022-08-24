package com.thiefspin.rank

import com.thiefspin.rank.league.models.GameResult

import scala.collection.immutable.ListMap

package object league {

  implicit val gameParser: Parser[GameResult] = (line: String) => {
    GameResult(line)
  }

  implicit class ScoreBoardSorter(scoreBoard: Map[String, Int]) {
    def sort(): Map[String, Int] = {
      ListMap(scoreBoard.toSeq
        .sortWith(_._1 < _._1)
        .sortWith(_._2 > _._2): _*)
    }
  }

  implicit class ScoreBoardFormatter(scoreBoard: Map[String, Int]) {
    def formatLines(): Iterable[String] = {
      scoreBoard.zipWithIndex.map { case ((team, score), count) =>
        s"${count + 1}. ${team}, ${score} ${if (score == 1) "pt" else "pts"}"
      }
    }
  }

}
