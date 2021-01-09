package me.farhan.moviecataloq.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.domain.model.TvShow
import me.farhan.moviecataloq.core.data.MovieCataloqRepository
import me.farhan.moviecataloq.core.util.DataDummy
import me.farhan.moviecataloq.core.data.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author farhan
 * created at at 11:26 on 02/11/2020.
 */
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

  private lateinit var viewModel: DetailViewModel

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Mock
  private lateinit var repository: _root_ide_package_.me.farhan.moviecataloq.core.data.MovieCataloqRepository

  private val dummyMovie = DataDummy.getMovies()[0]
  private val movieId = dummyMovie.id

  private val dummyTvShow = DataDummy.getTvShows()[0]
  private val tvShowId = dummyTvShow.id

  @Before
  fun setUp() {
    viewModel = DetailViewModel(repository)
    viewModel.setMovieId(movieId)
    viewModel.setTvShowId(tvShowId)
  }

  @Test
  fun getMovie() {
    val movie = MutableLiveData<Resource<Movie>>()
    val resource = Resource.success(dummyMovie)
    movie.value = resource

    `when`(repository.getMovieDetail(movieId)).thenReturn(movie)

    val observer = mock(Observer::class.java) as Observer<Resource<Movie>>
    viewModel.getMovie().observeForever(observer)
    verify(observer).onChanged(resource)
  }

  @Test
  fun getTvShow() {
    val tvShow = MutableLiveData<Resource<TvShow>>()
    val resource = Resource.success(dummyTvShow)
    tvShow.value = resource

    `when`(repository.getTvShowDetail(tvShowId)).thenReturn(tvShow)

    val observer = mock(Observer::class.java) as Observer<Resource<TvShow>>
    viewModel.getTvShow().observeForever(observer)
    verify(observer).onChanged(resource)
  }
}