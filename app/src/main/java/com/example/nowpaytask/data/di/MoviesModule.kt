package com.example.nowpaytask.data.di

import com.example.nowpaytask.data.remote.MoviesApi
import com.example.nowpaytask.data.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class MoviesModule {

    @Provides
    fun provideMoviesService(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    fun provideMoviesRemoteDataSource(
        moviesApi: MoviesApi,
    ): MoviesRepository =
        MoviesRepository(
            moviesApi,
        )

}