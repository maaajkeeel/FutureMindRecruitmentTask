package com.ambrozy.fma.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ambrozy.fma.base.BaseViewModel
import com.ambrozy.interactors.usecases.GetAllRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(getAllRecipesUseCase: GetAllRecipesUseCase) : BaseViewModel() {
  val text: LiveData<String> = MutableLiveData("Hello world")

  init {
    viewModelScope.launch {
      getAllRecipesUseCase.execute()
    }
  }
}