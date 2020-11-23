package me.farhan.moviecataloq.di

import androidx.lifecycle.ViewModelProvider
import me.farhan.moviecataloq.data.remote.MovieDataSource
import me.farhan.moviecataloq.data.repository.MovieCataloqRepository
import me.farhan.moviecataloq.network.ApiClient
import me.farhan.moviecataloq.network.ApiService

/**
 * @author farhan
 * created at at 19:57 on 21/11/20.
 */
object Injection {
    fun provideRepository(): MovieCataloqRepository {
        val apiService: ApiService = ApiClient.getService()
        val remoteDataSource = MovieDataSource.getInstance(apiService)
        return MovieCataloqRepository.getInstance(remoteDataSource)
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory.getInstance()
    }
}