# sbt-dog

[![Build Status](https://travis-ci.org/scala-kennel/sbt-dog.svg?branch=master)](https://travis-ci.org/scala-kennel/sbt-dog)

sbt plugin for [dog](https://github.com/scala-kennel/dog)

## latest stable version

`project/plugins.sbt`

```scala
addSbtPlugin("com.github.pocketberserker" % "sbt-dog" % "0.1.0")
```

`build.sbt`

```scala
dogSettings

dogVersion := "0.3.0"
```

or

```scala
dogWithGen

dogVersion := "0.3.0"
```

or

```scala
dogWithScalaprops

dogVersion := "0.3.0"
```

or

```scala
dogWithScalazlaws

dogVersion := "0.3.0"
scalapropsVersion := "0.3.1"
```

