# sbt-dog

[![Build Status](https://travis-ci.org/scala-kennel/sbt-dog.svg?branch=master)](https://travis-ci.org/scala-kennel/sbt-dog)

sbt plugin for [dog](https://github.com/scala-kennel/dog)

## latest stable version

`project/plugins.sbt`

```scala
addSbtPlugin("com.github.pocketberserker" % "sbt-dog" % "0.1.1")
```

### JVM

`build.sbt`

```scala
dogSettings

dogVersion := "0.6.0"
```

or

```scala
dogWithGen

dogVersion := "0.6.0"
```

or

```scala
dogWithScalaprops

dogVersion := "0.6.0"
```

or

```scala
dogWithScalazlaws

dogVersion := "0.6.0"
scalapropsVersion := "0.3.4"
```

### Scala.js

`build.sbt`

```scala
dogCoreSettings

libraryDependencies += "com.github.pocketberserker" %%% "dog" % "0.6.0" % "test"
```

or

```scala
dogCoreSettings

val dogVersion = "0.6.0"

libraryDependencies += "com.github.pocketberserker" %%% "dog" % dogVersion % "test"
libraryDependencies += "com.github.scalaprops" %%% "scalaprops" % scalapropsVersion % "test"
libraryDependencies += "com.github.pocketberserker" %%% "dog-gen" % dogVersion % "test"
libraryDependencies += "com.github.pocketberserker" %%% "dog-prop" % dogVersion % "test"
```
