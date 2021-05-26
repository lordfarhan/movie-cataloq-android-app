package me.farhan.moviecataloq.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import me.farhan.moviecataloq.core.data.source.local.entity.MovieEntity
import me.farhan.moviecataloq.core.data.source.local.entity.TvShowEntity

/**
 * @author farhan
 * created at at 11:22 on 28/11/20.
 */
@Database(
  entities = [MovieEntity::class, TvShowEntity::class],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun dao(): AppDao
}