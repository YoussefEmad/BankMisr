package com.banqurmisr.chanllenge5.data.source.remote

import com.banqurmisr.chanllenge5.data.models.RemoteMovieModel
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{endpoint}")
    suspend fun getMovies(@Path("endpoint") endpoint: String
                          ,@Query("page") page: Int): Response<RemoteMovieModel>


}