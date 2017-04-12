name := "creatures-athletic-meet"

version := "1.0"

scalaVersion := "2.12.1"

scalacOptions ++= Seq(
  "-Xlint",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  //  "-Ywarn-unused",
  //  "-Ywarn-unused-import",
  "-Ywarn-value-discard",
  "-Xelide-below", "ALL"
)
scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= (Seq(
) ++ Seq( // scalatest
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
))
