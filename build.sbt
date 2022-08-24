ThisBuild / scalaVersion := "2.13.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.thiefspin"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "rank-league-scala",
    Compile / run / mainClass := Some("com.thiefspin.rank.league.Application"),
    libraryDependencies ++= Seq(
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
      "ch.qos.logback" % "logback-classic" % "1.2.11",
      "com.typesafe" % "config" % "1.4.2",
      "org.scalatest" %% "scalatest" % "3.2.11" % Test
    )
  )
