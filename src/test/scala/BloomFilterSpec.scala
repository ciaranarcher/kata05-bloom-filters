package com.example.kata05bloomfilters

import org.scalatest._

class BloomFilterSpec extends FlatSpec with Matchers {

  it should "accept a set of words and return a set of 8 x 4 byte numbers (from a 32 byte SHA-256 hash) for each" in {
    val filter = new BloomFilter(1024)
    val bitmap = filter.loadItems(Array[String]("the", "quick", "brown", "fox"))
    val count = bitmap.count(_ == true)
    println(s"bits set: ${count}")

    val r1 = filter.query("the")
    r1.shouldBe(Some(true))

    val r2 = filter.query("missing")
    r2.shouldBe(None)
    /*
    We need to map this 4 byte int (between -2147483648 and 2147483648)
    to a number between 1 and 100000.

    Then we need to set these bits in an Array[Boolean].

    Then we need to write a query method that takes a word and returns if is is part of the set.
     */
  }
}
