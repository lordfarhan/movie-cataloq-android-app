package me.farhan.moviecataloq.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.farhan.moviecataloq.core.data.source.local.LocalDataSource
import me.farhan.moviecataloq.core.data.source.remote.RemoteDataSource
import me.farhan.moviecataloq.core.data.source.remote.response.ApiResponse
import me.farhan.moviecataloq.core.data.source.remote.response.MovieResponse
import me.farhan.moviecataloq.core.data.source.remote.response.TvShowResponse
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.domain.model.TvShow
import me.farhan.moviecataloq.core.domain.repository.IRepository
import me.farhan.moviecataloq.core.util.AppExecutors
import me.farhan.moviecataloq.core.util.DataMapper

/**
 * @author farhan
 * created at at 22:32 on 14/11/20.
 */
class AppRepository constructor(
  private val remoteDataSource: RemoteDataSource,
  private val localDataSource: LocalDataSource,
  private val appExecutors: AppExecutors
) : IRepository {
  override fun getMovies(): Flow<Resource<List<Movie>>> {
    return object :
      NetworkBoundResource<List<Movie>, List<MovieResponse>>(
        appExecutors
      ) {
      override fun loadFromDB(): Flow<List<Movie>> {
        return localDataSource.getMovies().map {
          DataMapper.mapMovieEntitiesToDomain(it)
        }
      }

      override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

      override fun onFetchFailed(msg: String?) {
      }

      override suspend fun saveCallResult(data: List<MovieResponse>) {
        val list = DataMapper.mapMovieResponsesToEntities(data)
        localDataSource.insertMovies(list)
      }

      override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
        remoteDataSource.fetchMovies()

    }.asFlow()
  }

  override fun getMovieDetail(id: Long): Flow<Resource<Movie>> {
    return object : NetworkBoundResource<Movie, MovieResponse>(
      appExecutors
    ) {
      override fun loadFromDB(): Flow<Movie> {
        return localDataSource.getMovieDetail(id).map {
          DataMapper.mapMovieEntityToDomain(it)
        }
      }

      override fun shouldFetch(data: Movie?): Boolean = data?.status.isNullOrEmpty()

      override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
        remoteDataSource.fetchMovieDetail(id)

      override fun onFetchFailed(msg: String?) {
      }

      override suspend fun saveCallResult(data: MovieResponse) =
        localDataSource.insertMovie(DataMapper.mapMovieResponseToEntity(data))

    }.asFlow()
  }

  override fun getTvShows(): Flow<Resource<List<TvShow>>> {
    return object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>(
      appExecutors
    ) {
      override fun loadFromDB(): Flow<List<TvShow>> {
        return localDataSource.getTvShows().map {
          DataMapper.mapTvShowEntitiesToDomain(it)
        }
      }

      override fun shouldFetch(data: List<TvShow>?): Boolean = data == null || data.isEmpty()

      override fun onFetchFailed(msg: String?) {
      }

      override suspend fun saveCallResult(data: List<TvShowResponse>) {
        val list = DataMapper.mapTvShowResponsesToEntities(data)
        localDataSource.insertTvShows(list)
      }

      override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
        remoteDataSource.fetchTvShows()

    }.asFlow()
  }

  override fun getTvShowDetail(id: Long): Flow<Resource<TvShow>> {
    return object : NetworkBoundResource<TvShow, TvShowResponse>(
        appExecutors
      ) {
      override fun loadFromDB(): Flow<TvShow> {
        return localDataSource.getTvShowDetail(id).map {
          DataMapper.mapTvShowEntityToDomain(it)
        }
      }

      override fun shouldFetch(data: TvShow?): Boolean = data?.status.isNullOrEmpty()

      override fun onFetchFailed(msg: String?) {
      }

      override suspend fun createCall(): Flow<ApiResponse<TvShowResponse>> =
        remoteDataSource.fetchTvShowDetail(id)

      override suspend fun saveCallResult(data: TvShowResponse) =
        localDataSource.insertTvShow(DataMapper.mapTvShowResponseToEntity(data))

    }.asFlow()
  }

  override fun getFavoriteMovies(): Flow<List<Movie>> {
    return localDataSource.getFavoriteMovies().map {
      DataMapper.mapMovieEntitiesToDomain(it)
    }
  }

  override fun getFavoriteTvShows(): Flow<List<TvShow>> {
    return localDataSource.getFavoriteTvShows().map {
      DataMapper.mapTvShowEntitiesToDomain(it)
    }
  }

  override fun addFavoriteMovie(id: Long) =
    appExecutors.diskIO().execute { localDataSource.insertFavoriteMovie(id) }

  override fun addFavoriteTvShow(id: Long) =
    appExecutors.diskIO().execute { localDataSource.insertFavoriteTvShow(id) }

  override fun deleteFavoriteMovie(id: Long) =
    appExecutors.diskIO().execute { localDataSource.deleteFavoriteMovie(id) }

  override fun deleteFavoriteTvShow(id: Long) =
    appExecutors.diskIO().execute { localDataSource.deleteFavoriteTvShow(id) }
}