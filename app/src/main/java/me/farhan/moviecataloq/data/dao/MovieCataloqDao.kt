package me.farhan.moviecataloq.data.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.model.TvShow

/**
 * @author farhan
 * created at at 10:58 on 28/11/20.
 */
@Dao
interface MovieCataloqDao {
  @Query("SELECT * FROM movies")
  fun getMovies(): DataSource.Factory<Int, Movie>

  @Query("SELECT * FROM tv_shows")
  fun getTvShows(): DataSource.Factory<Int, TvShow>

  @Query("SELECT * FROM movies WHERE id = :id")
  fun getMovieDetail(id: Long): LiveData<Movie>

  @Query("SELECT * FROM tv_shows WHERE id = :id")
  fun getTvShowDetail(id: Long): LiveData<TvShow>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovies(movies: List<Movie>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertTvShows(tvShows: List<TvShow>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovie(movie: Movie)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertTvShow(tvShow: TvShow)

  @Query("SELECT * FROM movies WHERE isFavorite = 1")
  fun getFavoriteMovies(): DataSource.Factory<Int, Movie>

  @Query("SELECT * FROM tv_shows WHERE isFavorite = 1")
  fun getFavoriteTvShows(): DataSource.Factory<Int, TvShow>

  @Query("UPDATE movies SET isFavorite = 1 WHERE id = :id")
  fun insertFavoriteMovie(id: Long)

  @Query("UPDATE tv_shows SET isFavorite = 1 WHERE id = :id")
  fun insertFavoriteTvShow(id: Long)

  @Query("UPDATE movies SET isFavorite = 0 WHERE id = :id")
  fun deleteFavoriteMovie(id: Long)

  @Query("UPDATE tv_shows SET isFavorite = 0 WHERE id = :id")
  fun deleteFavoriteTvShow(id: Long)
}