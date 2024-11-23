package com.example.nowpaytask.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


import okhttp3.Interceptor
import okhttp3.Response
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    internal fun provideHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.retryOnConnectionFailure(true)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(logging)
            .addInterceptor(AuthenticationInterceptor())
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url.newBuilder().addQueryParameter("api_key","c50f5aa4e7c95a2a553d29b81aad6dd0").build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}

