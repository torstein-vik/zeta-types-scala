scalaVersion := "2.12.3"

name := "zeta-types"

parallelExecution in Test := true

// Show warnings
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

// Dependencies
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.16"