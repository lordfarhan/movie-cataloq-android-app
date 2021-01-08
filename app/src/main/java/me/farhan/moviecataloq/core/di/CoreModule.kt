package me.farhan.moviecataloq.core.di

import androidx.room.Room
import me.farhan.moviecataloq.core.data.MovieCataloqRepository
import me.farhan.moviecataloq.core.data.source.local.LocalDataSource
import me.farhan.moviecataloq.core.data.source.local.room.AppDatabase
import me.farhan.moviecataloq.core.data.source.remote.RemoteDataSource
import me.farhan.moviecataloq.core.data.source.remote.network.ApiService
import me.farhan.moviecataloq.core.domain.repository.IRepository
import me.farhan.moviecataloq.core.util.AppExecutors
import me.farhan.moviecataloq.core.util.ENDPOINT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author farhan
 * created at at 9:58 on 05/01/21.
 */
val databaseModule = module {
  factory { get<AppDatabase>().dao() }
  single {
    Room.databaseBuilder(
      androidContext(),
      AppDatabase::class.java, "movie_cataloq_db"
    ).fallbackToDestructiveMigration().build()
  }
}

val networkModule = module {
  single {
    OkHttpClient.Builder()
      .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
      .connectTimeout(120, TimeUnit.SECONDS)
      .readTimeout(120, TimeUnit.SECONDS)
      .build()
  }
  single {
    val retrofit = Retrofit.Builder()
      .baseUrl(ENDPOINT)
      .addConverterFactory(GsonConverterFactory.create())
      .client(get())
      .build()
    retrofit.create(ApiService::class.java)
  }
}

val repositoryModule = module {
  single { LocalDataSource(get()) }
  single { RemoteDataSource(get()) }
  factory { AppExecutors() }
  single<IRepository> { MovieCataloqRepository(get(), get(), get()) }
}