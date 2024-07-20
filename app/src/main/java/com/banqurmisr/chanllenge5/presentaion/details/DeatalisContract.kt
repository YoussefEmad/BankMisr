package com.banqurmisr.chanllenge5.presentaion.details

import com.banqurmisr.chanllenge5.base.ViewAction
import com.banqurmisr.chanllenge5.base.ViewEvent
import com.banqurmisr.chanllenge5.base.ViewState
import com.banqurmisr.chanllenge5.data.models.LocalMovieModel
import com.banqurmisr.chanllenge5.domain.utils.Resource

class DeatalisContract {

    sealed class Action : ViewAction {
        data class GetMovie(val id :Int) : Action()
    }

    sealed class Event : ViewEvent {

        data class ShowError(val error: String) : Event()

    }

    data class State(
        val isLoading: Boolean = false,
        val movie: Resource<LocalMovieModel>? =null,
        val error: String? = null
    ) : ViewState

}



