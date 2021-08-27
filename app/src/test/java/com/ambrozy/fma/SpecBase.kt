package com.ambrozy.fma

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import io.kotest.core.spec.DslDrivenSpec
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.BehaviorSpec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

object FakeMainThreadExecutor : TaskExecutor() {

  override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

  override fun isMainThread() = true

  override fun postToMainThread(runnable: Runnable) = runnable.run()
}

@ExperimentalCoroutinesApi
open class CoroutinesBehaviorSpec : BehaviorSpec() {
  private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

  @ExperimentalCoroutinesApi
  override fun beforeSpec(spec: Spec) {
    ArchTaskExecutor.getInstance()
      .setDelegate(FakeMainThreadExecutor)
    super.beforeSpec(spec)
    Dispatchers.setMain(testDispatcher)
  }

  @ExperimentalCoroutinesApi
  override fun afterSpec(spec: Spec) {
    ArchTaskExecutor.getInstance()
      .setDelegate(null)
    super.afterSpec(spec)
    Dispatchers.resetMain()
    testDispatcher.cleanupTestCoroutines()
  }

  override fun isolationMode(): IsolationMode? {
    return IsolationMode.InstancePerLeaf
  }
}