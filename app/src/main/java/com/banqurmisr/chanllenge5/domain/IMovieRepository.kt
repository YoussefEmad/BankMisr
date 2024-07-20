package com.banqurmisr.chanllenge5.domain

import com.banqurmisr.chanllenge5.data.models.LocalMovieModel
import com.banqurmisr.chanllenge5.domain.models.MovieModel
import com.banqurmisr.chanllenge5.domain.utils.Resource

interface IMovieRepository{

    suspend fun getMovies(endpoint: String,page: Int): Resource<MovieModel>
    suspend fun getMovie(id: Int): Resource<LocalMovieModel>

}