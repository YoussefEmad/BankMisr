
package com.banqurmisr.chanllenge5.presentaion.home

import com.banqurmisr.chanllenge5.base.BaseViewModel
import com.banqurmisr.chanllenge5.base.ViewAction
import com.banqurmisr.chanllenge5.base.ViewEvent
import com.banqurmisr.chanllenge5.base.ViewState
import com.banqurmisr.chanllenge5.data.models.LocalMovieModel
import com.banqurmisr.chanllenge5.data.source.local.MovieDao
import com.banqurmisr.chanllenge5.domain.models.MovieResult
import com.banqurmisr.chanllenge5.domain.models.MovieTypes
import com.banqurmisr.chanllenge5.domain.usecases.GetMovieUseCase
import com.banqurmisr.chanllenge5.presentaion.MovieRemoteMediator


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import com.banqurmisr.chanllenge5.presentaion.home.HomeContractor.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val movieDao: MovieDao
) : BaseViewModel<State,Event,Action>(initialState = State()){


    init {
        fetchMovies(MovieTypes.PLAYING_NOW.value)
        fetchMovies(MovieTypes.POPULAR.value)
        fetchMovies(MovieTypes.UPCOMING.value)
    }
    override suspend fun handleAction(action: HomeContractor.Action) {
        when (action) {
            is HomeContractor.Action.GetMovies -> fetchMovies(action.Type)
            HomeContractor.Action.NavigateToDetails -> { /* Navigate to details */ }
        }
    }


    private fun fetchMovies(type: String) {
        viewModelScope.launch {
            val flow = getMoviesPaging(type)
            flow.collectLatest {
                when (type) {
                    MovieTypes.PLAYING_NOW.value -> setState { copy(nowPlayingMovies = flow) }
                    MovieTypes.POPULAR.value -> setState { copy(popularMovies = flow) }
                    MovieTypes.UPCOMING.value -> setState { copy(upcomingMovies = flow) }
                }
            }
        }
    }


    fun getMoviesPaging(type:String ): Flow<PagingData<LocalMovieModel>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                initialLoadSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = MovieRemoteMediator(
                getPagerBlogsUseCase = getMovieUseCase,
                movieDao = movieDao,
                type = type
            )
        )
        {
            movieDao.getAllMovie(type)
        }.flow.cachedIn(viewModelScope)
    }



}
