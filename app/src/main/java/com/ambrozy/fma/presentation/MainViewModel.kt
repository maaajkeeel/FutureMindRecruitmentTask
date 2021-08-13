package com.ambrozy.fma.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ambrozy.fma.R
import com.ambrozy.fma.base.BaseViewModel
import com.ambrozy.interactors.usecases.GetAllRecipesUseCase
import com.ambrozy.ui.BR
import com.ambrozy.ui.displayables.RecipeDisplayable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(getAllRecipesUseCase: GetAllRecipesUseCase) : BaseViewModel() {
  val text: MutableLiveData<String> = MutableLiveData("Hello world")
  val recipeDisplayables: MutableLiveData<List<RecipeDisplayable>> = MutableLiveData(emptyList())
  val recipeListBinding = ItemBinding.of<RecipeDisplayable>(BR.recipeDisplayable, R.layout.recipe_list_item)

  init {
    viewModelScope.launch {
      val recipes = getAllRecipesUseCase.execute()
      recipeDisplayables.postValue(recipes.map {
        if (it.imageUrl.isNotEmpty()) {
          Timber.d("Image url ${it.imageUrl}")
        }
        RecipeDisplayable(
          it.title,
          it.description,
          it.modificationDate,
          it.imageUrl
        )
      }
      )

      Timber.d("Size of recipe displayable ${recipeDisplayables.value!!.size}")
    }

  }
}