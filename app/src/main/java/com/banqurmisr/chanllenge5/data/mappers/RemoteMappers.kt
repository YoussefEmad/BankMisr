package com.banqurmisr.chanllenge5.data.mappers

import com.banqurmisr.chanllenge5.data.models.LocalMovieModel
import com.banqurmisr.chanllenge5.data.models.RemoteMovieModel
import com.banqurmisr.chanllenge5.data.models.RemoteResult
import com.banqurmisr.chanllenge5.domain.models.MovieModel
import com.banqurmisr.chanllenge5.domain.models.MovieResult


fun RemoteMovieModel.toDomain(): MovieModel {
    return MovieModel(
        page = page,
        movieResults = results?.map {
            it.toDomain()
        },
        total_pages = total_pages
    )
}

fun RemoteResult.toDomain(): MovieResult {
    return MovieResult(
        id = id ?: 0,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        vote_average = vote_average,
        vote_count = vote_count
    )
}

fun MovieResult.toLocal (type  :String) : LocalMovieModel{

    return LocalMovieModel(
        type = type,
        id = id ,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        vote_average =vote_average,
        vote_count =  vote_count
    )
}
fun LocalMovieModel.toDomain () : MovieResult{
    return MovieResult(
        id = id?:0 ,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        vote_average =vote_average,
        vote_count =  vote_count
    )
}