package me.farhan.moviecataloq.ui.detail

import me.farhan.moviecataloq.util.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * @author farhan
 * created at at 11:26 on 02/11/2020.
 */
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.getMovies()[0]
    private val movieId = dummyMovie.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.movie = DataDummy.getMovieById(movieId)
    }

    @Test
    fun getMovie() {
        val entity = viewModel.movie
        assertNotNull(entity)
        assertEquals(dummyMovie, entity)
    }
}