package me.farhan.moviecataloq

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
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

  private val NIGHT_MODE = "NIGHT_MODE"
  private var nightMode = AppCompatDelegate.getDefaultNightMode()

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

    val mPrefs = PreferenceManager.getDefaultSharedPreferences(this)
    nightMode = mPrefs.getInt(NIGHT_MODE, AppCompatDelegate.getDefaultNightMode())

    AppCompatDelegate.setDefaultNightMode(nightMode)
  }

  fun setIsNightModeEnabled(mode: Int) {
    nightMode = mode
    val mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    val editor = mPrefs.edit()
    editor.putInt(NIGHT_MODE, nightMode)
    editor.apply()
  }
}