package com.example.kata05bloomfilters

import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class BloomFilter(bitmapSize: Int) {
  val numMapper = new NumberMapper((Int.MinValue to Int.MaxValue - 1), (0 to bitmapSize - 1))
  val bitMap = Array.fill(bitmapSize)(false)

  def loadItems(items: Array[String]): Array[Boolean] = {
    items
      .map(hash)
      .map(getIntsFromHash)
      .map(mapToRange)
      .map(setInBitmap)

    bitMap
  }

  def query(word: String): Boolean = {
    mapToRange(getIntsFromHash(hash(word)))
      .view                           // Lazily find all items in the list
      .map(item => bitMap.lift(item))
      .forall(_ == Some(true))        // Return if all are found
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

  private def mapToRange(nums: Array[Int]) = {
    nums.map(numMapper.map)
  }

  private def hash(str: String) = {
    MessageDigest.getInstance("SHA-256").digest(str.getBytes(StandardCharsets.UTF_8))
  }

  private def setInBitmap(bits: Array[Int]) = {
    bits.map(i => bitMap.update(i, true))
  }
}

