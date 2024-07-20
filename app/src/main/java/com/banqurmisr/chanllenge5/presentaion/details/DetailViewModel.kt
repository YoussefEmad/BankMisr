package com.banqurmisr.chanllenge5.presentaion.details

import com.banqurmisr.chanllenge5.base.BaseViewModel
import com.banqurmisr.chanllenge5.domain.usecases.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (
    private val getMovieUseCase: GetMovieUseCase,

): BaseViewModel<DeatalisContract.State, DeatalisContract.Event, DeatalisContract.Action>(initialState = DeatalisContract.State(true, null, null))
{
    override suspend fun handleAction(action: DeatalisContract.Action) {
        when (action) {
            is DeatalisContract.Action.GetMovie -> fetchMovie(action.id)
        }
    }
    private suspend fun fetchMovie(id: Int) {
        val movie = getMovieUseCase.getMovie(id)
        setState { copy(isLoading = false, movie = movie) }
        }
    }

