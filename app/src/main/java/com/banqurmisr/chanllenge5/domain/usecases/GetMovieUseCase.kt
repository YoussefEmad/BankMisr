package com.banqurmisr.chanllenge5.domain.usecases

import com.banqurmisr.chanllenge5.data.models.LocalMovieModel
import com.banqurmisr.chanllenge5.domain.IMovieRepository
import com.banqurmisr.chanllenge5.domain.models.MovieModel
import com.banqurmisr.chanllenge5.domain.utils.Resource
import javax.inject.Inject

class GetMovieUseCase @Inject
constructor(val movieRepository: IMovieRepository) {


    suspend fun getMovies(endpoint: String,page: Int): Resource<MovieModel>{
        return movieRepository.getMovies(endpoint,page)
    }

    suspend fun getMovie(id: Int): Resource<LocalMovieModel>{
        return movieRepository.getMovie(id)
    }


}