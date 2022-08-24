package com.thiefspin.rank.league

import com.thiefspin.rank.league.models.GameResult
import com.typesafe.config.Config

class ScoreboardCalculator(results: List[GameResult], config: Config) {

  private val initialScore: Map[String, Int] = init()
  private val WINNING_POINTS: Int = config.getInt("game-rules.winning-points")
  private val DRAW_POINTS: Int = config.getInt("game-rules.drawing-points")

  def calculate(): Map[String, Int] = {
    results.foldLeft(initialScore) { case (scores, result) =>
      electWinner(result) match {
        case i if i > 0 => allocatePoints(scores, result.teamA.name, WINNING_POINTS)
        case i if i < 0 => allocatePoints(scores, result.teamB.name, WINNING_POINTS)
        case _ =>
          val sc = allocatePoints(scores, result.teamA.name, DRAW_POINTS)
          allocatePoints(sc, result.teamB.name, DRAW_POINTS)
      }
    }
  }

  private def allocatePoints(scores: Map[String, Int], name: String, points: Int): Map[String, Int] = {
    scores.get(name) match {
      case Some(score) => scores + (name -> (score + points))
      case None => scores + (name -> points)
    }
  }

  /**
   * Returns a positive int if team A won , negative if team B won and 0 if drawn
   */
  private def electWinner(result: GameResult): Int = {
    result.teamA.score.compare(result.teamB.score)
  }

  private def init(): Map[String, Int] = {
    val teams = results.foldLeft(List.empty[String]) { case (acc, result) =>
      acc ::: List(result.teamA.name, result.teamB.name)
    }.distinct
    teams.map(t => t -> 0).toMap
  }

}
