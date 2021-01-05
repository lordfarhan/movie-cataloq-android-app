package me.farhan.moviecataloq.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.repository.MovieCataloqRepository

/**
 * @author farhan
 * created at at 14:52 on 28/11/20.
 */
class FavoriteMovieViewModel(private val repository: MovieCataloqRepository) : ViewModel() {
  fun getFavoriteMovies(): LiveData<PagedList<Movie>> = repository.getFavoriteMovies()
}
