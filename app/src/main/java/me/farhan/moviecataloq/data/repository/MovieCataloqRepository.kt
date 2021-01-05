package me.farhan.moviecataloq.data.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.farhan.moviecataloq.App
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.data.NetworkBoundResource
import me.farhan.moviecataloq.data.local.LocalDataSource
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.model.TvShow
import me.farhan.moviecataloq.data.remote.RemoteDataSource
import me.farhan.moviecataloq.network.response.ApiResponse
import me.farhan.moviecataloq.util.AppExecutors
import me.farhan.moviecataloq.vo.Resource

/**
 * @author farhan
 * created at at 22:32 on 14/11/20.
 */
class MovieCataloqRepository private constructor(
  private val remoteDataSource: RemoteDataSource,
  private val localDataSource: LocalDataSource,
  private val appExecutors: AppExecutors
) {
  companion object {
    @Volatile
    private var instance: MovieCataloqRepository? = null
    fun getInstance(
      remoteSource: RemoteDataSource,
      localSource: LocalDataSource,
      executors: AppExecutors
    ): MovieCataloqRepository =
      instance ?: synchronized(this) {
        instance ?: MovieCataloqRepository(remoteSource, localSource, executors)
      }
  }

  fun getMovies(): LiveData<Resource<PagedList<Movie>>> {
    return object : NetworkBoundResource<PagedList<Movie>, List<Movie>>(appExecutors) {
      override fun loadFromDB(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
          .setEnablePlaceholders(false)
          .setInitialLoadSizeHint(4)
          .setPageSize(4)
          .build()
        return LivePagedListBuilder(localDataSource.getMovies(), config).build()
      }

      override fun shouldFetch(data: PagedList<Movie>?): Boolean = data == null || data.isEmpty()

      override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
        remoteDataSource.fetchMovies()

      override fun saveCallResult(data: List<Movie>) = localDataSource.insertMovies(data)

      override fun onFetchFailed(msg: String?) {
        Toast.makeText(
          App.getContext(),
          msg ?: App.getContext().resources.getString(R.string.dialog_error),
          Toast.LENGTH_SHORT
        ).show()
      }

    }.asLiveData()
  }

  fun getMovieDetail(id: Long): LiveData<Resource<Movie>> {
    return object : NetworkBoundResource<Movie, Movie>(appExecutors) {
      override fun loadFromDB(): LiveData<Movie> = localDataSource.getMovieDetail(id)

      override fun shouldFetch(data: Movie?): Boolean = data?.status.isNullOrEmpty()

      override fun createCall(): LiveData<ApiResponse<Movie>> =
        remoteDataSource.fetchMovieDetail(id)

      override fun saveCallResult(data: Movie) = localDataSource.insertMovie(data)

      override fun onFetchFailed(msg: String?) {
        Toast.makeText(
          App.getContext(),
          msg ?: App.getContext().resources.getString(R.string.dialog_error),
          Toast.LENGTH_SHORT
        ).show()
      }

    }.asLiveData()
  }


  fun getTvShows(): LiveData<Resource<PagedList<TvShow>>> {
    return object : NetworkBoundResource<PagedList<TvShow>, List<TvShow>>(appExecutors) {
      override fun loadFromDB(): LiveData<PagedList<TvShow>> {
        val config = PagedList.Config.Builder()
          .setEnablePlaceholders(false)
          .setInitialLoadSizeHint(4)
          .setPageSize(4)
          .build()
        return LivePagedListBuilder(localDataSource.getTvShows(), config).build()
      }

      override fun shouldFetch(data: PagedList<TvShow>?): Boolean = data == null || data.isEmpty()

      override fun createCall(): LiveData<ApiResponse<List<TvShow>>> =
        remoteDataSource.fetchTvShows()

      override fun saveCallResult(data: List<TvShow>) = localDataSource.insertTvShows(data)

      override fun onFetchFailed(msg: String?) {
        Toast.makeText(
          App.getContext(),
          msg ?: App.getContext().resources.getString(R.string.dialog_error),
          Toast.LENGTH_SHORT
        ).show()
      }

    }.asLiveData()
  }

  fun getTvShowDetail(id: Long): LiveData<Resource<TvShow>> {
    return object : NetworkBoundResource<TvShow, TvShow>(appExecutors) {
      override fun loadFromDB(): LiveData<TvShow> = localDataSource.getTvShowDetail(id)

      override fun shouldFetch(data: TvShow?): Boolean = data?.status.isNullOrEmpty()

      override fun createCall(): LiveData<ApiResponse<TvShow>> =
        remoteDataSource.fetchTvShowDetail(id)

      override fun saveCallResult(data: TvShow) = localDataSource.insertTvShow(data)

      override fun onFetchFailed(msg: String?) {
        Toast.makeText(
          App.getContext(),
          msg ?: App.getContext().resources.getString(R.string.dialog_error),
          Toast.LENGTH_SHORT
        ).show()
      }

    }.asLiveData()
  }

  fun getFavoriteMovies(): LiveData<PagedList<Movie>> {
    val config = PagedList.Config.Builder()
      .setEnablePlaceholders(false)
      .setInitialLoadSizeHint(4)
      .setPageSize(4)
      .build()
    return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
  }

  fun getFavoriteTvShows(): LiveData<PagedList<TvShow>> {
    val config = PagedList.Config.Builder()
      .setEnablePlaceholders(false)
      .setInitialLoadSizeHint(4)
      .setPageSize(4)
      .build()
    return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
  }

  fun addFavoriteMovie(id: Long) =
    appExecutors.diskIO().execute { localDataSource.insertFavoriteMovie(id) }

  fun addFavoriteTvShow(id: Long) =
    appExecutors.diskIO().execute { localDataSource.insertFavoriteTvShow(id) }

  fun deleteFavoriteMovie(id: Long) =
    appExecutors.diskIO().execute { localDataSource.deleteFavoriteMovie(id) }

  fun deleteFavoriteTvShow(id: Long) =
    appExecutors.diskIO().execute { localDataSource.deleteFavoriteTvShow(id) }
}