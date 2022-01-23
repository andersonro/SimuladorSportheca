package br.com.arodevsistemas.simuladorsportheca.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.arodevsistemas.simuladorsportheca.data.model.Match
import br.com.arodevsistemas.simuladorsportheca.domain.MatchUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MatchViewModel(private val matchUseCase: MatchUseCase): ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun getMatchListRepository() {
        viewModelScope.launch {
            matchUseCase.getMatchList()
                .onStart {
                    _state.postValue(State.Loading)
                }
                .catch {
                    _state.postValue(State.Error(it))
                }
                .collect {
                    _state.postValue(State.Success(it))
                }
        }
    }

    sealed class State{
        object Loading: State()
        data class Success(val list: List<Match>): State()
        data class Error(val error: Throwable): State()
    }

}