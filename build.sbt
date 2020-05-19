
ThisBuild / organization := "com.hansblackcat"
ThisBuild / scalaVersion := "2.13.2"
ThisBuild / version := "0.1.0"

lazy val root = (project in file("."))
    .settings(
        name := "Chess with Scala"
    )


lazy val scalaFX = (project in file("scalaFX"))
    .settings(
        libraryDependencies ++= Seq(
            "org.scalafx" %% "scalafx" % "14-R19"
        )
    )