package doodle.core

final case class BoundingBox(left: Double, top: Double, right: Double, bottom: Double) {

  override def toString: String = {
    s"left-top (x: $left, y: $top) right-bottom (x: $right, y: $bottom), Height: $height, Width $width"
  }

  val height: Double = top - bottom
  val width: Double = right - left

  def above(that: BoundingBox): BoundingBox = {
    println("BoundingBox above...")
    println(s" this: $this")
    println(s" that: $that")
    val box = BoundingBox(
      left min that.left,
      (height + that.height) / 2,
      right max that.right,
      -(height + that.height) / 2
    )
    println(s"above: $box")
    box
  }

  def beside(that: BoundingBox): BoundingBox = {
    BoundingBox(
      -(width + that.width) / 2,
      top max that.top,
      (width + that.width) / 2,
      bottom min that.bottom
    )
  }

  def on(that: BoundingBox): BoundingBox = {
    BoundingBox(
      left min that.left,
      top max that.top,
      right max that.right,
      bottom min that.bottom
    )
  }
}