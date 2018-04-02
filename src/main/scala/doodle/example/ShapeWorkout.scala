package doodle.example

import doodle.core._
import doodle.jvm.Java2DCanvas

object ShapeWorkout {

  val c10 = Circle(10)
  val c20 = Circle(20)
  val c30 = Circle(30)

  val twoCircleStack = c10 above c20
  val threeCircleStack = c10 above c20 above c30
  val threeCircleConcentric = c10 on c20 on c30

  private val r1 = Rectangle(100, 50)
  private val r2 = Rectangle(200, 100)
  private val r3 = Rectangle(150, 75)

  val twoRectangleStack = r1 above r2
  val threeRectangleStack = r1 above r2 above r3
  val threeRectangleConcentric = r1 on r2 on r3

  val concentricShapes = threeCircleConcentric on threeRectangleConcentric

  val circleSandwich = r1 beside c10 beside r1
  val rectangleSandwich = c30 beside r2 beside c30

  def main(args: Array[String]): Unit = {
    val canvas = Java2DCanvas.canvas
    canvas.setSize(500, 500)
    canvas.setOrigin(0, 0)

    (rectangleSandwich above circleSandwich beside concentricShapes).draw(canvas)
  }
}

/*
canvas.clear(Color.white)
*/