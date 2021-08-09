package com.ambrozy.data.base

interface Mapper<From, To> {
  fun map(from: From): To
}

fun <From, To> List<From>.mapAll(mapper: Mapper<From, To>) = this.map {
  mapper.map(it)
}