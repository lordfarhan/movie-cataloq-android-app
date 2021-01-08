package me.farhan.moviecataloq

import android.app.Application
import android.content.Context
import me.farhan.moviecataloq.core.di.databaseModule
import me.farhan.moviecataloq.core.di.networkModule
import me.farhan.moviecataloq.core.di.repositoryModule
import me.farhan.moviecataloq.di.useCaseModule
import me.farhan.moviecataloq.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @author farhan
 * created at at 10:39 on 15/11/20.
 */
class App : Application() {

  companion object {
    private lateinit var instance: App

    fun getInstance(): App {
      return instance
    }

    fun getContext(): Context {
      return instance.applicationContext
    }
  }

  override fun onCreate() {
    instance = this
    super.onCreate()

    startKoin {
      androidLogger(Level.NONE)
      androidContext(this@App)
      modules(
        listOf(
          databaseModule,
          networkModule,
          repositoryModule,
          useCaseModule,
          viewModelModule
        )
      )
    }
  }
}