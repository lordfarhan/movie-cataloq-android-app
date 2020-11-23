package me.farhan.moviecataloq.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.farhan.moviecataloq.data.model.TvShow
import me.farhan.moviecataloq.data.repository.MovieCataloqRepository

/**
 * @author farhan
 * created at at 13:40 on 27/10/2020.
 */
class TvShowViewModel(private val repository: MovieCataloqRepository) : ViewModel() {
    private val tvShows = MutableLiveData<List<TvShow>>()
    fun getTvShows(): LiveData<List<TvShow>> {
        val _tvShows = repository.getTvShows()
        tvShows.postValue(_tvShows.value)
        return tvShows
    }
}