package me.farhan.moviecataloq.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.farhan.moviecataloq.data.model.TvShow
import me.farhan.moviecataloq.data.repository.MovieCataloqRepository
import me.farhan.moviecataloq.vo.Resource

/**
 * @author farhan
 * created at at 13:40 on 27/10/2020.
 */
class TvShowViewModel(private val repository: MovieCataloqRepository) : ViewModel() {
  fun getTvShows(): LiveData<Resource<PagedList<TvShow>>> = repository.getTvShows()
}