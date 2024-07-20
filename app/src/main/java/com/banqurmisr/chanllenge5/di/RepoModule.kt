package com.banqurmisr.chanllenge5.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.banqurmisr.chanllenge5.data.MovieRepository
import com.banqurmisr.chanllenge5.data.source.local.AppDatabase
import com.banqurmisr.chanllenge5.data.source.local.MovieDao
import com.banqurmisr.chanllenge5.data.source.remote.IRemoteDataSource
import com.banqurmisr.chanllenge5.data.source.remote.MovieApi
import com.banqurmisr.chanllenge5.data.source.remote.RemoteDataSource
import com.banqurmisr.chanllenge5.data.utils.Constants
import com.banqurmisr.chanllenge5.domain.IMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(ViewModelComponent::class)
@Module
object RepoModule {


    @Provides
    fun provideRemoteDateSource(movieApi: MovieApi): IRemoteDataSource {
        return RemoteDataSource(movieApi)
    }
    @Provides
    fun provideMovieRepository(iRemoteDataSource: IRemoteDataSource , moveDao: MovieDao ): IMovieRepository {
        return MovieRepository(iRemoteDataSource,moveDao)
    }



}