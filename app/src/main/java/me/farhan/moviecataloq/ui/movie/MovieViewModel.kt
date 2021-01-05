package me.farhan.moviecataloq.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.repository.MovieCataloqRepository
import me.farhan.moviecataloq.vo.Resource

/**
 * @author farhan
 * created at at 13:52 on 23/10/2020.
 */
class MovieViewModel(private val repository: MovieCataloqRepository) : ViewModel() {
  fun getMovies(): LiveData<Resource<PagedList<Movie>>> = repository.getMovies()
}
