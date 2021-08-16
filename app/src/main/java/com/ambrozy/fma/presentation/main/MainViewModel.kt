package com.ambrozy.fma.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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
class MainViewModel @Inject constructor(getAllRecipesUseCase: GetAllRecipesUseCase) : BaseViewModel<Event>() {
  val recipeDisplayables: MutableLiveData<List<RecipeDisplayable>> = MutableLiveData(emptyList())

  private val recipeClickHandler = object : RecipeClickHandler {
    override fun click(recipeDisplayable: RecipeDisplayable) {
      viewModelScope.launch {
        eventChannel.send(Event.NavigateToWebViewScreen(recipeDisplayable.redirectionLink))
      }
    }
  }

  val recipeListBinding = ItemBinding.of<RecipeDisplayable>(BR.recipeDisplayable, R.layout.recipe_list_item)
    .bindExtra(BR.clickHandler, recipeClickHandler)

  init {
    viewModelScope.launch {
      val recipes = getAllRecipesUseCase.execute()
      recipeDisplayables.postValue(recipes.map {
        RecipeDisplayable(
          it.title,
          it.description,
          it.modificationDate,
          it.imageUrl,
          it.redirectionLink
        )
      }
      )
    }
  }
}