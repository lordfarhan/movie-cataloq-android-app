package me.farhan.moviecataloq.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.repository.MovieCataloqRepository
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
 * created at at 13:01 on 01/12/20.
 */
@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {

  private lateinit var viewModel: FavoriteMovieViewModel

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Mock
  private lateinit var repository: MovieCataloqRepository

  @Mock
  private lateinit var observer: Observer<PagedList<Movie>>

  @Mock
  private lateinit var pagedList: PagedList<Movie>

  @Before
  fun setUp() {
    viewModel = FavoriteMovieViewModel(repository)
  }

  @Test
  fun getFavoriteMovies() {
    val dummyMovies = pagedList
    `when`(dummyMovies.size).thenReturn(10)

    val favMovies = MutableLiveData<PagedList<Movie>>()
    favMovies.value = dummyMovies

    `when`(repository.getFavoriteMovies()).thenReturn(favMovies)
    val entities = viewModel.getFavoriteMovies().value
    verify(repository).getFavoriteMovies()

    assertNotNull(entities)
    assertEquals(10, entities?.size)

    viewModel.getFavoriteMovies().observeForever(observer)
    verify(observer).onChanged(dummyMovies)
  }
}