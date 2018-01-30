scalaVersion := "2.12.3"

name := "zeta-types"

parallelExecution in Test := false

// Show warnings
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Ywarn-unused", "-Xlint")

// Show test time usage
testOptions in Test += Tests.Argument("-oD")

// Dependencies
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6"
