package doodle.example

import doodle.core.BoundingBox

object BoundingBoxWorkout {
  def main(args: Array[String]): Unit = {
    above(BoundingBox(3, 6, 6, 3), BoundingBox(3, 6, 6, 3))
    above(BoundingBox(2, 4, 6, 2), BoundingBox(2, 6, 4, 4))
    above(BoundingBox(5, 5, 10, 0), BoundingBox(0, 15, 15, 10))

    beside(BoundingBox(2, 6, 4, 2), BoundingBox(2, 4, 6, 4))

    on(BoundingBox(2, 6, 4, 2), BoundingBox(2, 4, 6, 4))
  }

  def above(bb1: BoundingBox, bb2: BoundingBox) = {
    println(s"bb1: $bb1")
    println(s"bb2: $bb2")
    println(s"bb1.above(bb2): ${bb1.above(bb2)}")
    println(s"bb2.above(bb1): ${bb2.above(bb1)}")
  }

  def beside(bb1: BoundingBox, bb2: BoundingBox) = {
    println(s"bb1: $bb1")
    println(s"bb2: $bb2")
    println(s"bb1.beside(bb2): ${bb1.beside(bb2)}")
    println(s"bb2.beside(bb1): ${bb2.beside(bb1)}")
  }

  def on(bb1: BoundingBox, bb2: BoundingBox) = {
    println(s"bb1: $bb1")
    println(s"bb2: $bb2")
    println(s"bb1.on(bb2): ${bb1.on(bb2)}")
    println(s"bb2.on(bb1): ${bb2.on(bb1)}")
  }
}