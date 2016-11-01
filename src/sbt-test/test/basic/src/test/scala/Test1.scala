package com.example

import java.nio.charset.StandardCharsets
import dog._
import java.nio.file.{Files, Paths}

// port from https://github.com/scalaprops/sbt-scalaprops
// license: MIT
// author: Kenji Yoshida <https://github.com/xuwei-k>
object Test1 extends Dog with Assert {

  private[this] def write(file: String, text: String) =
    Files.write(Paths.get(file), text.getBytes(StandardCharsets.UTF_8))

  val test1 = TestCase {
    write("test1.txt", "test1")
    pass(())
  }

  val test2 = TestCase {
    write("test2.txt", "test2")
    pass(())
  }

  lazy val `test 3` = TestCase {
    write("test3.txt", "test3")
    pass(())
  }
}
