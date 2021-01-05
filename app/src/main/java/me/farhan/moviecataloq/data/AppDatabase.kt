package me.farhan.moviecataloq.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.farhan.moviecataloq.data.dao.MovieCataloqDao
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.model.TvShow

/**
 * @author farhan
 * created at at 11:22 on 28/11/20.
 */
@Database(
  entities = [Movie::class, TvShow::class],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun dao(): MovieCataloqDao

  companion object {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase =
      INSTANCE ?: synchronized(this) {
        INSTANCE ?: Room.databaseBuilder(
          context.applicationContext,
          AppDatabase::class.java,
          "movie_cataloq_db"
        ).fallbackToDestructiveMigration().build()
      }
  }
}