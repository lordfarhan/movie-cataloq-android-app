package me.farhan.moviecataloq.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import me.farhan.moviecataloq.core.domain.model.TvShow
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
 * created at at 13:00 on 01/12/20.
 */
@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest {

  private lateinit var viewModel: me.farhan.moviecataloq.favorites.tvshow.FavoriteTvShowViewModel

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Mock
  private lateinit var repository: _root_ide_package_.me.farhan.moviecataloq.core.data.MovieCataloqRepository

  @Mock
  private lateinit var observer: Observer<PagedList<TvShow>>

  @Mock
  private lateinit var pagedList: PagedList<TvShow>

  @Before
  fun setUp() {
    viewModel = me.farhan.moviecataloq.favorites.tvshow.FavoriteTvShowViewModel(repository)
  }

  @Test
  fun getFavoriteMovies() {
    val dummyTvShows = pagedList
    `when`(dummyTvShows.size).thenReturn(10)

    val favTvShows = MutableLiveData<PagedList<TvShow>>()
    favTvShows.value = dummyTvShows

    `when`(repository.getFavoriteTvShows()).thenReturn(favTvShows)
    val entities = viewModel.getFavoriteTvShows().value
    verify(repository).getFavoriteTvShows()

    assertNotNull(entities)
    assertEquals(10, entities?.size)

    viewModel.getFavoriteTvShows().observeForever(observer)
    verify(observer).onChanged(dummyTvShows)
  }
}