package com.banqurmisr.chanllenge5.data.source.remote

import com.banqurmisr.chanllenge5.data.models.RemoteMovieModel
import com.banqurmisr.chanllenge5.domain.utils.Resource

interface IRemoteDataSource {

    suspend fun getMovies(endpoint: String,page: Int): Resource<RemoteMovieModel>

}