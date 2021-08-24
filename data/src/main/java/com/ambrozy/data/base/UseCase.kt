package com.ambrozy.data.base

interface InOutUseCase<in I : Any, out T : Any> {
  suspend fun execute(input: I): Result<T>
}