package dog

import sbt._, Keys._
import sbt.complete.Parser
import sbt.complete.DefaultParsers._
import sbinary.DefaultProtocol._
import scala.reflect.NameTransformer

// port from https://github.com/scalaprops/sbt-scalaprops
// license: MIT
// author: Kenji Yoshida <https://github.com/xuwei-k>
object DogPlugin extends AutoPlugin {

  private[this] val defaultParser: Parser[DogTest] = (
    Space ~> token(StringBasic, _ => true) ~
    (Space ~> token(StringBasic, _ => true)).*
  ).map(DogTest.tupled)

  object autoImport {
    val dogTestNames = TaskKey[Map[String, Set[String]]]("dogTestNames")
    val dogOnly = InputKey[Unit]("dogOnly")
    val dogVersion = SettingKey[String]("dogVersion")

    val dogCoreSettings: Seq[Setting[_]] = Seq(
      dogTestNames := {
        val loader = (testLoader in Test).value
        val runnerName = "dog.DogRunner"
        getSingletonInstance(runnerName, loader) match {
          case Right(clazz) =>
            val instance = clazz.getField("MODULE$").get(null)
            val method = clazz.getMethod("testFieldNames", classOf[Class[_]])
            val testNames = (definedTestNames in Test).value
            testNames.map { testName =>
              val testClass = Class.forName(testName, true, loader)
              val testFields = method.invoke(instance, testClass).asInstanceOf[Array[String]]
              testName -> testFields.toSet
            }(collection.breakOut)
          case Left(e) =>
            streams.value.log.debug(runnerName + " could not found")
            Map.empty
        }
      },
      dogTestNames <<= {
        dogTestNames storeAs dogTestNames triggeredBy (compile in Test)
      },
      dogOnly <<= InputTask.createDyn(
        Defaults.loadForParser(dogTestNames)(
          (state, classes) => classes.fold(defaultParser)(createParser)
        )
      ) {
        Def.task { test =>
          (testOnly in Test).toTask((" " :: test.className :: "--" :: "--only" :: test.methodNames.toList).mkString(" "))
        }
      }
    )

    val dogSettings: Seq[Setting[_]] = dogCoreSettings ++ Seq(
      testFrameworks += new TestFramework("dog.DogFramework"),
      libraryDependencies += "com.github.pocketberserker" %% "dog" % dogVersion.value % "test"
    )

    val dogWithGen: Seq[Setting[_]] = dogSettings ++ Seq(
      libraryDependencies += "com.github.pocketberserker" %% "dog-gen" % dogVersion.value % "test"
    )
  }

  final case class DogTest(className: String, methodNames: Seq[String])

  private[this] def createParser(tests: Map[String, Set[String]]): Parser[DogTest] = {
    tests.filter(_._2.nonEmpty).map { case (k, v) =>
      val (noChange, changed) = v.partition(n => NameTransformer.decode(n) == n)
      val all = noChange ++ changed.map(n => "\"" + NameTransformer.decode(n) + "\"")
      val parser = token(k) ~ distinctParser(all)
      parser.map(DogTest.tupled)
    }.reduceOption(_ | _).map(Space ~> _).getOrElse(defaultParser)
  }

  private[this] def getSingletonInstance(objectName: String, loader: ClassLoader): Either[Throwable, Class[_]] =
    try {
      Right(Class.forName(objectName + "$", true, loader))
    } catch {
      case e: ClassNotFoundException =>
        Left(e)
    }

  private[this] def distinctParser(exs: Set[String]): Parser[Seq[String]] = {
    val base = token(Space) ~> token(NotSpace examples exs)
    base.flatMap { ex =>
      val (_, notMatching) = exs.partition(GlobFilter(ex).accept)
      distinctParser(notMatching).map { result => ex +: result }
    } ?? Nil
  }
}
