package com.thiefspin.rank.league.models

import com.thiefspin.rank.league.exceptions.ParseException
import com.thiefspin.rank.league.models

final case class GameResult(
  teamA: TeamScore,
  teamB: TeamScore
)

object GameResult {

  @throws[ParseException](cause = "Line was not in the expected format for the parser")
  def apply(line: String): GameResult = {
    val teams = line.split(",").map { team =>
      if (team.last.isLetter) {
        throw new ParseException(line)
      }
      TeamScore(team.dropRight(1).trim, team.last.asDigit)
    }
    models.GameResult(teams.head, teams.last)
  }
}
