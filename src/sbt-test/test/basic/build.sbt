// port from https://github.com/scalaprops/sbt-scalaprops
// license: MIT
// author: Kenji Yoshida <https://github.com/xuwei-k>

dogSettings

dogVersion := System.getProperty("dog.version")

val scala211 = "2.11.7"

scalaVersion := scala211

crossScalaVersions := scala211 :: "2.10.5" :: Nil

scalacOptions ++= (
  "-deprecation" ::
  "-unchecked" ::
  "-Xlint" ::
  "-language:existentials" ::
  "-language:higherKinds" ::
  "-language:implicitConversions" ::
  Nil
)
