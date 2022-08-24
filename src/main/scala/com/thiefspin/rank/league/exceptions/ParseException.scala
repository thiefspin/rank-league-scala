package com.thiefspin.rank.league.exceptions

class ParseException(message: String, cause: Option[Throwable]) extends Exception(message, cause.orNull) {

  def this(line: String) = this(s"Failed to parse line: $line", None)

}
