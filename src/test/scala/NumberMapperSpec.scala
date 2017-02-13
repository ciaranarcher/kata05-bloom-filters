package com.example.kata05bloomfilters

import org.scalatest._
/*
A number mapper will take two ranges:
 - The first (target) range is the range you wish to map into.
 - The second (source) range is the range you wish to map from.
 */
class NumberMapperSpec extends FunSpec with Matchers {

  describe("mapping a middling number works") {
    val mapper = new NumberMapper((1000 to 2000), (1 to 100))
    it("maps the middle of a range correctly") {
      val result = mapper.map(1500)
      result.shouldBe(51)
    }

    it("maps the bottom of the range correctly") {
      val result = mapper.map(1000)
      result.shouldBe(1)
    }

    it("maps the top of the range correctly") {
      val result = mapper.map(2000)
      result.shouldBe(100)
    }

    it("errors if the number is not in the input range") {
      an [IndexOutOfBoundsException] should be thrownBy {
        mapper.map(3000)
      }
    }
  }

  describe("for a range of integer values") {
    val mapper = new NumberMapper((Int.MinValue to Int.MaxValue - 1), (0 to 1023))

    it("maps the middle of a range correctly") {
      val result = mapper.map(0)
      result.shouldBe(512)
    }

    it("maps the bottom of the range correctly") {
      val result = mapper.map(Int.MinValue)
      result.shouldBe(0)
    }

    it("maps the top of the range correctly") {
      val result = mapper.map(Int.MaxValue - 1)
      result.shouldBe(1023)
    }
  }
}
