package com.example

import dog._
import scalaprops._
import scalaz.std.anyVal._
import scalaz.std.tuple._

object Test1 extends Dog with Assert {

  final case class Point[A](x: A, y: A)

  object Point {

    implicit def gen: Gen[Point[Int]] =
      Gen[(Int, Int)].map{
        case (x, y) if x == y => Point(x, y + 1)
        case (x, y) => Point(x, y)
      }
  }

  val point = Parameterize((t: (Int, Int)) => TestCase {
    pred(t._1 != t._2)
  })
}
