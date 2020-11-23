package me.farhan.moviecataloq

import android.app.Application
import android.content.Context

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

        var isShowingDialog: Boolean = false
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}