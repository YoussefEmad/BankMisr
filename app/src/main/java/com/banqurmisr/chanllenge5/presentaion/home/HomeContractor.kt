
package com.banqurmisr.chanllenge5.presentaion.home

import androidx.paging.PagingData
import com.banqurmisr.chanllenge5.base.ViewAction
import com.banqurmisr.chanllenge5.base.ViewEvent
import com.banqurmisr.chanllenge5.base.ViewState
import com.banqurmisr.chanllenge5.data.models.LocalMovieModel
import com.banqurmisr.chanllenge5.domain.models.MovieResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow


class HomeContractor {

    sealed class Action : ViewAction {
       data class GetMovies(var Type: String) : Action()
        object NavigateToDetails : Action()

    }

    sealed class Event : ViewEvent {

        data class ShowError(val error: String) : Event()

    }

    data class State(
        val isLoading: Boolean = false,
        val nowPlayingMovies: Flow<PagingData<LocalMovieModel>> = emptyFlow(),
        val popularMovies: Flow<PagingData<LocalMovieModel>> = emptyFlow(),
        val upcomingMovies: Flow<PagingData<LocalMovieModel>> = emptyFlow()
    ) : ViewState
}
