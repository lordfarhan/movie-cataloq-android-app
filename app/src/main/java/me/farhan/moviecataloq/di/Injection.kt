package me.farhan.moviecataloq.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import me.farhan.moviecataloq.data.AppDatabase
import me.farhan.moviecataloq.data.local.LocalDataSource
import me.farhan.moviecataloq.data.remote.RemoteDataSource
import me.farhan.moviecataloq.data.repository.MovieCataloqRepository
import me.farhan.moviecataloq.network.ApiClient
import me.farhan.moviecataloq.network.ApiService
import me.farhan.moviecataloq.util.AppExecutors

/**
 * @author farhan
 * created at at 19:57 on 21/11/20.
 */
object Injection {
  fun provideRepository(context: Context): MovieCataloqRepository {
    val apiService: ApiService = ApiClient.getService()
    val database: AppDatabase = AppDatabase.getInstance(context)

    val remoteDataSource = RemoteDataSource.getInstance(apiService)
    val localDataSource = LocalDataSource.getInstance(database.dao())
    val appExecutors = AppExecutors()

    return MovieCataloqRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
  }

  fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
    return ViewModelFactory.getInstance(context)
  }
}