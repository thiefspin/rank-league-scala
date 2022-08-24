package com.thiefspin.rank.league

import scala.io.BufferedSource

class CustomFileReader[A](source: BufferedSource)(implicit parser: Parser[A]) {

  private val lines: Iterator[String] = source.getLines()

  def read(): List[A] = {
    traverseFile(parser.parseLine).toList
  }

  private def traverseFile(f: String => A): Iterator[A] = {
    lines.map(f.apply)
  }

}
