package me.farhan.moviecataloq.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.farhan.moviecataloq.data.model.TvShow
import me.farhan.moviecataloq.data.repository.MovieCataloqRepository

/**
 * @author farhan
 * created at at 16:19 on 28/11/20.
 */
class FavoriteTvShowViewModel(private val repository: MovieCataloqRepository) : ViewModel() {
  fun getFavoriteTvShows(): LiveData<PagedList<TvShow>> = repository.getFavoriteTvShows()
}
