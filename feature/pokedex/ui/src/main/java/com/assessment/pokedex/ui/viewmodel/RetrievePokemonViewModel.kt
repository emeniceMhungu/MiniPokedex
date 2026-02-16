package com.assessment.pokedex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assessment.common.domain.model.BaseResult
import com.assessment.pokedex.domain.usecase.RetrievePokemonListUseCase
import com.assessment.pokedex.ui.mapper.toMessageResId
import com.assessment.pokedex.ui.mapper.toUiModels
import com.assessment.pokedex.ui.model.PokemonListEvent
import com.assessment.pokedex.ui.model.PokemonListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetrievePokemonViewModel @Inject constructor(
    private val retrievePokemonListUseCase: RetrievePokemonListUseCase
) : ViewModel() {
    companion object {
        private const val INITIAL_LIMIT = 100
        private const val INITIAL_OFFSET = 0
    }

    private var _uiState =
        MutableStateFlow<PokemonListUiState>(PokemonListUiState.Empty)
    val uiState: StateFlow<PokemonListUiState> = _uiState.asStateFlow()


    fun onEvent(event: PokemonListEvent) {
        when (event) {
            is PokemonListEvent.LoadPokemonList -> {
                retrievePokemonList()
            }

            is PokemonListEvent.OnPokemonClicked -> {
                //do nothing already handled in navigation
            }

            PokemonListEvent.Retry -> {
                retrievePokemonList()
            }
        }
    }

    private fun retrievePokemonList() {
        viewModelScope.launch {
            retrievePokemonListUseCase(
                limit = INITIAL_LIMIT,
                offset = INITIAL_OFFSET
            )
                .collect { result ->
                    when (result) {
                        is BaseResult.Loading -> {
                            _uiState.value = PokemonListUiState.Loading
                        }

                        is BaseResult.Failure -> {
                            _uiState.value = PokemonListUiState.Error(
                                result.error.toMessageResId()
                            )
                        }

                        is BaseResult.Success -> {
                            _uiState.value = if (result.data.isEmpty()) {
                                PokemonListUiState.Empty
                            } else {
                                PokemonListUiState.Success(
                                    result.data.toUiModels()
                                )
                            }
                        }
                    }
                }
        }
    }
}
