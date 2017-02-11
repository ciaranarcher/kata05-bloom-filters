package com.example.kata05bloomfilters

import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class BloomFilter {
  def loadItems(items: Array[String]): Array[Array[Int]] = {
    items
      .map(hash)
      .map(getIntsFromHash)
  }

  private def asInt(slice: Array[Byte]) = {
    ByteBuffer.wrap(slice).getInt
  }

  private def getIntsFromHash(hash: Array[Byte]) = {
    Array(
      asInt(hash.slice(0, 4)),
      asInt(hash.slice(4, 8)),
      asInt(hash.slice(8, 12)),
      asInt(hash.slice(12, 16)),
      asInt(hash.slice(16, 20)),
      asInt(hash.slice(20, 24)),
      asInt(hash.slice(24, 28)),
      asInt(hash.slice(28, 32))
    )

  }

  private def hash(str: String) = {
    println(s"hashing: $str")
    MessageDigest.getInstance("SHA-256").digest(str.getBytes(StandardCharsets.UTF_8))
  }
}

