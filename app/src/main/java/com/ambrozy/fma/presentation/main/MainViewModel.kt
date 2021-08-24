package com.ambrozy.fma.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ambrozy.data.base.mapAll
import com.ambrozy.domain.RecipeEntity
import com.ambrozy.fma.R
import com.ambrozy.fma.base.BaseViewModel
import com.ambrozy.interactors.usecases.GetAllRecipesUseCase
import com.ambrozy.ui.BR
import com.ambrozy.ui.displayables.RecipeClickHandler
import com.ambrozy.ui.displayables.RecipeDisplayable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val getAllRecipesUseCase: GetAllRecipesUseCase,
  private val recipeEntityToDisplayableMapper: RecipeEntityToDisplayableMapper
) : BaseViewModel<Event>() {
  val recipeDisplayables: MutableLiveData<List<RecipeDisplayable>> = MutableLiveData(emptyList())
  val loading: MutableLiveData<Boolean> = MutableLiveData(false)

  private val recipeClickHandler = object : RecipeClickHandler {
    override fun click(recipeDisplayable: RecipeDisplayable) {
      viewModelScope.launch {
        if (loading.value == false) {
          eventChannel.send(Event.NavigateToWebViewScreen(recipeDisplayable.redirectionLink))
        }
      }
    }
  }

  val recipeListBinding = ItemBinding.of<RecipeDisplayable>(BR.recipeDisplayable, R.layout.recipe_list_item)
    .bindExtra(BR.clickHandler, recipeClickHandler)

  init {
    viewModelScope.launch {
      loading.postValue(true)
      attachRecipes(getRecipes())
      loading.postValue(false)
    }
  }

  private suspend fun getRecipes(refresh: Boolean = false): List<RecipeEntity> {
    val result = getAllRecipesUseCase
      .execute(refresh)
    result.onSuccess { recipes ->
      return recipes.sortedBy { it.orderId }
    }
      .onFailure {
        it.message?.let { eventChannel.send(Event.ShowToast(it)) }
      }
    return emptyList()
  }

  private fun attachRecipes(recipes: List<RecipeEntity>) {
    recipes.takeIf { it.isNotEmpty() }?.let { recipes ->
      recipeDisplayables.postValue(
        recipes.mapAll(recipeEntityToDisplayableMapper)
      )
    }
  }

  fun refreshClicked() {
    viewModelScope.launch {
      loading.postValue(true)
      attachRecipes(getRecipes(refresh = true))
      loading.postValue(false)
    }
  }
}