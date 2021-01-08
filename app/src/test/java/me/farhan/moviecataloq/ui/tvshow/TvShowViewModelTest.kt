package me.farhan.moviecataloq.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import me.farhan.moviecataloq.core.domain.model.TvShow
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
 * created at at 11:16 on 02/11/2020.
 */
@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

  private lateinit var viewModel: TvShowViewModel

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Mock
  private lateinit var repository: MovieCataloqRepository

  @Mock
  private lateinit var observer: Observer<Resource<PagedList<TvShow>>>

  @Mock
  private lateinit var pagedList: PagedList<TvShow>

  @Before
  fun setUp() {
    viewModel = TvShowViewModel(repository)
  }

  @Test
  fun getTvShows() {
    val dummyTvShows = Resource.success(pagedList)
    `when`(dummyTvShows.data?.size).thenReturn(10)

    val tvShows = MutableLiveData<Resource<PagedList<TvShow>>>()
    tvShows.value = dummyTvShows

    `when`(repository.getTvShows()).thenReturn(tvShows)
    val entities = viewModel.getTvShows().value?.data
    verify(repository).getTvShows()

    assertNotNull(entities)
    assertEquals(10, entities?.size)

    viewModel.getTvShows().observeForever(observer)
    verify(observer).onChanged(dummyTvShows)
  }
}