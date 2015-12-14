# sbt-dog

[![Build Status](https://travis-ci.org/pocketberserker/sbt-dog.svg?branch=master)](https://travis-ci.org/pocketberserker/sbt-dog)

sbt plugin for [dog](https://github.com/scala-kennel/dog)

## latest stable version

`project/plugins.sbt`

```scala
addSbtPlugin("com.github.pocketberserker" % "sbt-dog" % "0.1.0")
```

`build.sbt`

```scala
dogSettings

dogVersion := "0.2.0"
```

or

```scala
dogWithGen

dogVersion := "0.2.0"
```

or

```scala
dogWithScalaprops

dogVersion := "0.2.0"
```

or

```scala
dogWithScalazlaws

dogVersion := "0.2.0"
scalapropsVersion := "0.2.0"
```

