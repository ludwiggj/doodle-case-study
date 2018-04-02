package doodle.core

import doodle.backend.Canvas

sealed trait Image {
  val boundingBox: BoundingBox = {
    this match {
      case Circle(r) => BoundingBox(-r, r, r, -r)
      case Rectangle(w, h) => BoundingBox(-w / 2, h / 2, w / 2, -h / 2)
      case On(subject, underneath) => subject.boundingBox on underneath.boundingBox
      case Above(subject, below) => subject.boundingBox above below.boundingBox
      case Beside(subject, right) => subject.boundingBox beside right.boundingBox
    }
  }

  def on(that: Image): Image = On(this, that)

  def above(that: Image): Image = Above(this, that)

  def beside(that: Image): Image = Beside(this, that)

  def draw(canvas: Canvas, originX: Double, originY: Double): Unit = {
    def stroke() = {
      println("Stroking canvas")
      canvas.setStroke(Stroke(3.0, Color.black, Line.Cap.Round, Line.Join.Round))
      canvas.stroke()
    }

    def displayBoundingBoxCoordinates(box: BoundingBox, aBox: BoundingBox, bBox: BoundingBox) = {
      println("Draw above...")
      println(s" box: $box")
      println(s"aBox: $aBox")
      println(s"bBox: $bBox")
    }

    def displayNewCoordinates(first: Double, second: Double, axes: String): Unit = {
      println(s"New $axes co-ords: $first $second")
    }

    this match {
      case Circle(radius) =>
        println(s"Drawing circle at: ($originX, $originY), radius: [$radius]")
        canvas.circle(originX, originY, radius)
        stroke()
      case Rectangle(width, height) =>
        //        canvas.rectangle(width / 2, height / 2, width, height)
        canvas.rectangle(originX, originY, width, height)
        stroke()
      case Above(a, b) =>
        val box = this.boundingBox
        val aBox = a.boundingBox
        val bBox = b.boundingBox

        displayBoundingBoxCoordinates(box, aBox, bBox)

        val aboveOriginY = originY + box.top - (aBox.height / 2)
        val belowOriginY = originY + box.bottom + (bBox.height / 2)

        displayNewCoordinates(aboveOriginY, belowOriginY, "Y")

        a.draw(canvas, originX, aboveOriginY)
        b.draw(canvas, originX, belowOriginY)
      case On(o, u) =>
        o.draw(canvas, originX, originY)
        u.draw(canvas, originX, originY)
      case Beside(l, r) =>
        val box = this.boundingBox
        val lBox = l.boundingBox
        val rBox = r.boundingBox

        displayBoundingBoxCoordinates(box, lBox, rBox)

        val leftOriginX = originX + box.left + (lBox.width / 2)
        val rightOriginX = originX + box.right - (rBox.width / 2)

        displayNewCoordinates(leftOriginX, rightOriginX, "X")

        l.draw(canvas, leftOriginX, originY)
        r.draw(canvas, rightOriginX, originY)
    }
  }

  def draw(canvas: Canvas): Unit = {
    draw(canvas, 0, 0)
  }
}

final case class Circle(radius: Double) extends Image

final case class Rectangle(width: Double, height: Double) extends Image

final case class On(subject: Image, underneath: Image) extends Image

final case class Above(subject: Image, below: Image) extends Image

final case class Beside(subject: Image, right: Image) extends Image