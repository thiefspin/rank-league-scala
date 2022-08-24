package com.thiefspin.rank.league

import com.thiefspin.rank.league.models.GameResult
import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.LazyLogging

import scala.io.{BufferedSource, Source}

object ScoreBoard extends LazyLogging {
  private val config: Config = ConfigFactory.load()
  private val filePath: String = config.getString("file.location")

  def run(): Unit = {
    val fileSource: BufferedSource = sourceFile()
    logger.info("Found file. Parsing the file for game results")
    val reader = new CustomFileReader[GameResult](fileSource)
    val results: List[GameResult] = reader.read()
    logger.info("Found game results! Generating the scoreboard")
    val ranker = new ScoreboardCalculator(results, config)
    ranker
      .calculate()
      .sort()
      .formatLines()
      .foreach(println)
    logger.info("Done!")
  }

  private def sourceFile(): BufferedSource = {
    logger.info(s"Sourcing the game file from: $filePath")
    if (filePath.isEmpty) {
      logger.info("No GAME_FILE_PATH set! Using the sample file")
      Source.fromResource("sample_input.txt")
    } else Source.fromFile(filePath)
  }

}
