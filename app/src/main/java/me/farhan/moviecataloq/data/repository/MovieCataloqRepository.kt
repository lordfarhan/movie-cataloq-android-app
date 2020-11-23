package me.farhan.moviecataloq.data.repository

import me.farhan.moviecataloq.data.remote.MovieDataSource

/**
 * @author farhan
 * created at at 22:32 on 14/11/20.
 */
class MovieCataloqRepository private constructor(private val movieDataSource: MovieDataSource) {
    companion object {
        @Volatile
        private var instance: MovieCataloqRepository? = null
        fun getInstance(dataSource: MovieDataSource): MovieCataloqRepository =
            instance ?: synchronized(this) {
                instance ?: MovieCataloqRepository(dataSource)
            }
    }

    fun getMovies() = movieDataSource.fetchMovies()

    fun getTvShows() = movieDataSource.fetchTvShows()
}