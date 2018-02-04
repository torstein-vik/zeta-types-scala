scalaVersion := "2.12.3"

name := "zeta-types"


// Scalac config
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Ywarn-unused", "-Xlint")

// Test config
testOptions in Test += Tests.Argument("-oD")
parallelExecution in Test := false

// Dependencies
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6"
