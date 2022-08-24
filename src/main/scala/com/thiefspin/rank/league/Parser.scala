package com.thiefspin.rank.league

trait Parser[A] {
  def parseLine(line: String): A
}
