package com.assessment.pokedex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assessment.common.domain.model.BaseResult
import com.assessment.pokedex.domain.usecase.GetPokemonDetailsUseCase
import com.assessment.pokedex.ui.mapper.toMessageResId
import com.assessment.pokedex.ui.mapper.toUiModel
import com.assessment.pokedex.ui.model.PokemonDetailsEvent
import com.assessment.pokedex.ui.model.PokemonDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GetPokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<PokemonDetailsUiState>(PokemonDetailsUiState.Loading)

    val uiState: StateFlow<PokemonDetailsUiState> = _uiState.asStateFlow()

    fun onEvent(event: PokemonDetailsEvent){
        when(event){
            is PokemonDetailsEvent.LoadPokemonDetails -> {
                getPokemonDetails(event.id)
            }
        }
    }

    private fun getPokemonDetails(id: Int) {
        viewModelScope.launch {
            getPokemonDetailsUseCase(id).collect { result ->
                when (result) {
                    is BaseResult.Failure -> {
                        Timber.d("Error: ${result.error}")
                        _uiState.value = PokemonDetailsUiState.Error(result.error.toMessageResId())
                    }
                    is BaseResult.Loading -> {
                        Timber.d("Loading")
                        _uiState.value = PokemonDetailsUiState.Loading
                    }
                    is BaseResult.Success -> {
                        Timber.d("Success: ${result.data}")
                        _uiState.value = PokemonDetailsUiState.Success(result.data.toUiModel())
                    }
                }
            }
        }
    }
}