package com.example.kata05bloomfilters

import org.scalatest._

class BloomFilterSpec extends FlatSpec with Matchers {

  it should "allow querying for words in a set" in {
    val filter = new BloomFilter(1024)
    val bitmap = filter.loadItems(Array[String]("the", "quick", "brown", "fox"))

    filter.query("the").shouldBe(true)
    filter.query("quick").shouldBe(true)
    filter.query("brown").shouldBe(true)
    filter.query("fox").shouldBe(true)

    filter.query("missing").shouldBe(false)
    filter.query("").shouldBe(false)
    filter.query("fog").shouldBe(false)
  }
}
