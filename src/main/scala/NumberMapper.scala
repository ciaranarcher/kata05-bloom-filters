package com.example.kata05bloomfilters

class NumberMapper(input: Range, output: Range) {
  val inRange = input
  val outRange = output

  def map(number: Int): Int = {
    input.contains(number) match {
      case true => mapNumber(number, inRange.start, inRange.end, outRange.start, outRange.end).toInt
      case _ => throw new IndexOutOfBoundsException(s"${number} is not in the input range ${input}")
    }
  }

  // TODO camelcase
  private def mapNumber(x: Float, in_min: Float, in_max: Float , out_min: Float, out_max: Float) = {
    Math.round((x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min)
  }
}
