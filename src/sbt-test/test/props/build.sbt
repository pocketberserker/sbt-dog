dogWithScalazlaws

dogVersion := System.getProperty("dog.version")
scalapropsVersion := System.getProperty("scalaprops.version")

crossScalaVersions := "2.11.7" :: "2.10.5" :: Nil

scalacOptions ++= (
  "-deprecation" ::
  "-unchecked" ::
  "-Xlint" ::
  "-language:existentials" ::
  "-language:higherKinds" ::
  "-language:implicitConversions" ::
  Nil
)
