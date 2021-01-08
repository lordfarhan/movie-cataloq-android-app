package me.farhan.moviecataloq.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.farhan.moviecataloq.core.data.source.local.entity.MovieEntity
import me.farhan.moviecataloq.core.data.source.local.entity.TvShowEntity

/**
 * @author farhan
 * created at at 10:58 on 28/11/20.
 */
@Dao
interface MovieCataloqDao {
  @Query("SELECT * FROM movies")
  fun getMovies(): Flow<List<MovieEntity>>

  @Query("SELECT * FROM tv_shows")
  fun getTvShows(): Flow<List<TvShowEntity>>

  @Query("SELECT * FROM movies WHERE id = :id")
  fun getMovieDetail(id: Long): Flow<MovieEntity>

  @Query("SELECT * FROM tv_shows WHERE id = :id")
  fun getTvShowDetail(id: Long): Flow<TvShowEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMovies(movies: List<MovieEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertTvShows(tvShows: List<TvShowEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMovie(movie: MovieEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertTvShow(tvShow: TvShowEntity)

  @Query("SELECT * FROM movies WHERE isFavorite = 1")
  fun getFavoriteMovies(): Flow<List<MovieEntity>>

  @Query("SELECT * FROM tv_shows WHERE isFavorite = 1")
  fun getFavoriteTvShows(): Flow<List<TvShowEntity>>

  @Query("UPDATE movies SET isFavorite = 1 WHERE id = :id")
  fun insertFavoriteMovie(id: Long)

  @Query("UPDATE tv_shows SET isFavorite = 1 WHERE id = :id")
  fun insertFavoriteTvShow(id: Long)

  @Query("UPDATE movies SET isFavorite = 0 WHERE id = :id")
  fun deleteFavoriteMovie(id: Long)

  @Query("UPDATE tv_shows SET isFavorite = 0 WHERE id = :id")
  fun deleteFavoriteTvShow(id: Long)
}