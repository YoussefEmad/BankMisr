package com.banqurmisr.chanllenge5.data

import com.banqurmisr.chanllenge5.data.mappers.toDomain
import com.banqurmisr.chanllenge5.data.models.LocalMovieModel
import com.banqurmisr.chanllenge5.data.source.local.MovieDao
import com.banqurmisr.chanllenge5.data.source.remote.IRemoteDataSource
import com.banqurmisr.chanllenge5.domain.IMovieRepository
import com.banqurmisr.chanllenge5.domain.models.MovieModel
import com.banqurmisr.chanllenge5.domain.utils.Resource
import javax.inject.Inject

class MovieRepository @Inject constructor(val remoteDataSource: IRemoteDataSource,val moveDao: MovieDao)
    : IMovieRepository {
    override suspend fun getMovies(endpoint: String,page: Int): Resource<MovieModel> {
        val movieData = remoteDataSource.getMovies(endpoint,page)
         return if (movieData.isSuccess()){
           Resource.success( movieData.getSuccessData().toDomain())
        }else{
            return movieData as Resource.Failure
        }
    }

    override suspend fun getMovie(id: Int): Resource<LocalMovieModel> {

        return Resource.success( moveDao.getMovie(id) )
    }


}