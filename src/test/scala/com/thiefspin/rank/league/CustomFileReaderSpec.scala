package com.thiefspin.rank.league

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.io.Source

class CustomFileReaderSpec extends AnyFlatSpec with Matchers {

  final case class SimpleParser(key: Int, value: String)

  "CustomFileReader " should "read a file " in {
    //Given
    val fileSource = Source.fromResource("sample_test_input.txt")
    implicit val parser: Parser[SimpleParser] = (line: String) => {
      val split = line.split(",")
      SimpleParser(split.head.toInt, split.last)
    }

    //When
    val reader = new CustomFileReader[SimpleParser](fileSource)
    val result = reader.read()

    //Then
    assert(result.size == 6) //check if the whole file was read
    result.foreach { record =>
      assert(record.value == s"test_string_${record.key}")
    }
  }

}
