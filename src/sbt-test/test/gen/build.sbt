dogWithGen

dogVersion := System.getProperty("dog.version")

crossScalaVersions := "2.11.8" :: "2.10.6" :: "2.12.0" :: Nil

scalacOptions ++= (
  "-deprecation" ::
  "-unchecked" ::
  "-Xlint" ::
  "-language:existentials" ::
  "-language:higherKinds" ::
  "-language:implicitConversions" ::
  Nil
)
