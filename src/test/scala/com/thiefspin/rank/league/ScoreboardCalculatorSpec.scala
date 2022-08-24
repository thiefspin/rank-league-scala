package com.thiefspin.rank.league

import com.thiefspin.rank.league.models.{GameResult, TeamScore}
import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ScoreboardCalculatorSpec extends AnyFlatSpec with Matchers {

  private val testConfig: Config = ConfigFactory.load("test-application.conf")

  private val results: List[GameResult] = List(
    GameResult(TeamScore("TeamA", 1), TeamScore("TeamB", 2)),
    GameResult(TeamScore("TeamC", 1), TeamScore("TeamA", 2)),
    GameResult(TeamScore("TeamD", 1), TeamScore("TeamE", 4)),
    GameResult(TeamScore("TeamE", 5), TeamScore("TeamC", 2)),
    GameResult(TeamScore("TeamE", 2), TeamScore("TeamA", 2))
  )

  "RankingCalculatorSpec" should "calculate the ranking " in {
    //Given
    val ranker = new ScoreboardCalculator(results, testConfig)

    //When
    val scoreBoard = ranker.calculate()

    //Then
    assert(scoreBoard("TeamA") == 4)
    assert(scoreBoard("TeamB") == 3)
    assert(scoreBoard("TeamC") == 0)
    assert(scoreBoard("TeamD") == 0)
    assert(scoreBoard("TeamE") == 7)
  }

  it should "sort the scoreboard " in {
    //Given
    val ranker = new ScoreboardCalculator(results, testConfig)

    //When
    val scoreBoard = ranker.calculate().sort()

    //Then
    scoreBoard.zipWithIndex.foreach { case ((team, score), index) =>
      if (index == 0) {
        assert(team == "TeamE")
        assert(score == 7)
      }
      if (index == 1) {
        assert(team == "TeamA")
        assert(score == 4)
      }
      if (index == 2) {
        assert(team == "TeamB")
        assert(score == 3)
      }
      if (index == 3) {
        assert(team == "TeamC")
        assert(score == 0)
      }
      if (index == 4) {
        assert(team == "TeamD")
        assert(score == 0)
      }
    }
  }

}
