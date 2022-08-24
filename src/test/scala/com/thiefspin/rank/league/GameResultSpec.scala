package com.thiefspin.rank.league

import com.thiefspin.rank.league.exceptions.ParseException
import com.thiefspin.rank.league.models.GameResult
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GameResultSpec extends AnyFlatSpec with Matchers {

  "GameResult " should "parse a line successfully " in {
    //Given
    val line = "Lions 3, Snakes 1"

    //When
    val result = GameResult(line)

    //Then
    assert(result.teamA.name == "Lions")
    assert(result.teamB.name == "Snakes")
    assert(result.teamA.score == 3)
    assert(result.teamB.score == 1)
  }

  it should "parse a line successfully 2 " in {
    //Given
    val line = "Lions3,Snakes1"

    //When
    val result = GameResult(line)

    //Then
    assert(result.teamA.name == "Lions")
    assert(result.teamB.name == "Snakes")
    assert(result.teamA.score == 3)
    assert(result.teamB.score == 1)
  }

  it should "fail to parse a line " in {
    //Given
    val line = "Lions3,Snakes"

    //Then
    the [ParseException] thrownBy(GameResult(line)) should have message(s"Failed to parse line: $line")
  }

}
