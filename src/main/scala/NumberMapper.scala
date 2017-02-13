package com.example.kata05bloomfilters

import java.lang.IndexOutOfBoundsException

class NumberMapper(input: Range, output: Range) {
  val inRange = input
  val outRange = output
  val slope = 1.0 * (output.end - output.start) / (input.end - input.start)

  def map(number: Int): Double = {
    input.contains(number) match {
      case true => Math.floor(outRange.start + slope * (number - inRange.start)).toInt
      case _ => throw new IndexOutOfBoundsException(s"${number} is not in the input range ${input}")
    }
  }
}
