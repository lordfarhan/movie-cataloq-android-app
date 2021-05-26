package me.farhan.moviecataloq.core.di

import androidx.room.Room
import me.farhan.moviecataloq.core.data.AppRepository
import me.farhan.moviecataloq.core.data.source.local.LocalDataSource
import me.farhan.moviecataloq.core.data.source.local.room.AppDatabase
import me.farhan.moviecataloq.core.data.source.remote.RemoteDataSource
import me.farhan.moviecataloq.core.data.source.remote.network.ApiService
import me.farhan.moviecataloq.core.domain.repository.IRepository
import me.farhan.moviecataloq.core.util.AppExecutors
import me.farhan.moviecataloq.core.util.ENDPOINT
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
    val passphrase: ByteArray = SQLiteDatabase.getBytes("farhan".toCharArray())
    val factory = SupportFactory(passphrase)
    Room.databaseBuilder(
      androidContext(),
      AppDatabase::class.java, "movie_cataloq_db"
    ).fallbackToDestructiveMigration()
      .openHelperFactory(factory)
      .build()
  }
}

val networkModule = module {
  single {
    val hostname = "api.themoviedb.org"
    val certificatePinner = CertificatePinner.Builder()
      .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
      .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
      .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
      .add(hostname, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
      .build()
    OkHttpClient.Builder()
      .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
      .connectTimeout(120, TimeUnit.SECONDS)
      .readTimeout(120, TimeUnit.SECONDS)
      .certificatePinner(certificatePinner)
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
  single<IRepository> {
    AppRepository(
      get(),
      get(),
      get()
    )
  }
}