package com.ambrozy.fma.presentation.main

import com.ambrozy.domain.RecipeEntity
import com.ambrozy.fma.presentation.RecipeEntityToDisplayableMapper
import com.ambrozy.interactors.usecases.GetAllRecipesUseCase
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class MainViewModelTest : BehaviorSpec() {
  init {
    isolationMode = IsolationMode.InstancePerLeaf
    Given("getting all recipes use case is succeeding with list") {
      val givenGetAllRecipesUseCase: GetAllRecipesUseCase = givenGetAllRecipesUseCaseSuccess

      When("view model starts") {
        val viewModel = createViewModel(getAllRecipesUseCase = givenGetAllRecipesUseCase)

        Then("recipes are fetched with correct parameter") {
          coVerify { givenGetAllRecipesUseCase.execute(eq(false)) }
        }

        And("refresh is clicked") {
          viewModel.refreshClicked()

          Then("recipes are fetched with correct parameters") {
            coVerify { givenGetAllRecipesUseCase.execute(eq(true)) }
          }
        }
      }
    }

    Given("getting all recipes use case is failing") {
      val givenErrorMessage = "ERROR"
      val givenGetAllRecipesUseCase: GetAllRecipesUseCase = mockk {
        coEvery { execute(any()) } returns Result.failure(Throwable(givenErrorMessage))
      }

      When("view model starts") {
        val viewModel = createViewModel(getAllRecipesUseCase = givenGetAllRecipesUseCase)

        Then("recipes are fetched with correct parameter") {
          coVerify { givenGetAllRecipesUseCase.execute(eq(false)) }
        }

        Then("event to show toast is emitted") {
          viewModel.eventChannel.tryReceive().getOrNull() shouldBe Event.ShowToast(givenErrorMessage)
        }
      }
    }
  }

  private val givenRecipes = listOf(
    RecipeEntity(
      description = "description",
      imageUrl = "http://url.pl",
      modificationDate = 11L,
      orderId = 1,
      title = "Title",
      redirectionLink = "https://redirection.pl"
    ),
    RecipeEntity(
      description = "description",
      imageUrl = "http://url.pl",
      modificationDate = 11L,
      orderId = 0,
      title = "Title",
      redirectionLink = "https://redirection.pl"
    ),
    RecipeEntity(
      description = "description",
      imageUrl = "http://url.pl",
      modificationDate = 11L,
      orderId = 3,
      title = "Title",
      redirectionLink = "https://redirection.pl"
    )
  )

  private val givenGetAllRecipesUseCaseSuccess: GetAllRecipesUseCase = mockk {
    coEvery { execute(any()) } returns Result.success(givenRecipes)
  }

  private fun createViewModel(
    getAllRecipesUseCase: GetAllRecipesUseCase = givenGetAllRecipesUseCaseSuccess
  ) = MainViewModel(
    getAllRecipesUseCase = getAllRecipesUseCase,
    recipeEntityToDisplayableMapper = RecipeEntityToDisplayableMapper()
  )
}