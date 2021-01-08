package me.farhan.moviecataloq.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.data.MovieCataloqRepository
import me.farhan.moviecataloq.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author farhan
 * created at at 11:08 on 02/11/2020.
 */
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

  private lateinit var viewModel: MovieViewModel

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Mock
  private lateinit var repository: MovieCataloqRepository

  @Mock
  private lateinit var observer: Observer<Resource<PagedList<Movie>>>

  @Mock
  private lateinit var pagedList: PagedList<Movie>

  @Before
  fun setUp() {
    viewModel = MovieViewModel(repository)
  }

  @Test
  fun getMovies() {
    val dummyMovies = Resource.success(pagedList)
    `when`(dummyMovies.data?.size).thenReturn(10)

    val movies = MutableLiveData<Resource<PagedList<Movie>>>()
    movies.value = dummyMovies

    `when`(repository.getMovies()).thenReturn(movies)
    val entities = viewModel.getMovies().value?.data
    verify(repository).getMovies()

    assertNotNull(entities)
    assertEquals(10, entities?.size)

    viewModel.getMovies().observeForever(observer)
    verify(observer).onChanged(dummyMovies)
  }
}