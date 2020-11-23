package me.farhan.moviecataloq.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.repository.MovieCataloqRepository

/**
 * @author farhan
 * created at at 13:52 on 23/10/2020.
 */
class MovieViewModel(private val repository: MovieCataloqRepository) : ViewModel() {
    private val movies = MutableLiveData<List<Movie>>()
    fun getMovies(): LiveData<List<Movie>> {
        val _movies = repository.getMovies()
        movies.postValue(_movies.value)
        return movies
    }
}
