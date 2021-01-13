package me.farhan.moviecataloq.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import me.farhan.moviecataloq.core.data.source.local.LocalDataSource
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.domain.model.TvShow
import me.farhan.moviecataloq.core.data.source.remote.RemoteDataSource
import me.farhan.moviecataloq.core.util.AppExecutors
import me.farhan.moviecataloq.core.util.DataDummy
import me.farhan.moviecataloq.core.util.mockPagedList
import me.farhan.moviecataloq.core.data.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * @author farhan
 * created at at 13:34 on 01/12/20.
 */
class MovieCataloqRepositoryTest {

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  private val remote = mock(RemoteDataSource::class.java)
  private val local = mock(LocalDataSource::class.java)
  private val executors = mock(AppExecutors::class.java)

  private val repository = FakeRepository(remote, local, executors)

  @Test
  fun getMovies() {
    val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
    `when`(local.getMovies()).thenReturn(dataSourceFactory)
    repository.getMovies()

    val entities = Resource.success(mockPagedList(DataDummy.getMovies()))
    verify(local).getMovies()
    assertNotNull(entities.data)
    assertEquals(10, entities.data.size)
  }

  @Test
  fun getTvShows() {
    val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShow>
    `when`(local.getTvShows()).thenReturn(dataSourceFactory)
    repository.getTvShows()

    val entities = Resource.success(mockPagedList(DataDummy.getTvShows()))
    verify(local).getTvShows()
    assertNotNull(entities.data)
    assertEquals(10, entities.data.size)
  }

}