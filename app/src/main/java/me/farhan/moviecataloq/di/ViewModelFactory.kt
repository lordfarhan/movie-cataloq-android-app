package me.farhan.moviecataloq.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.farhan.moviecataloq.data.repository.MovieCataloqRepository
import me.farhan.moviecataloq.ui.detail.DetailViewModel
import me.farhan.moviecataloq.ui.movie.MovieViewModel
import me.farhan.moviecataloq.ui.tvshow.TvShowViewModel

/**
 * @author farhan
 * created at at 20:10 on 21/11/20.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val repository: MovieCataloqRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) ->
                MovieViewModel(repository) as T
            modelClass.isAssignableFrom(TvShowViewModel::class.java) ->
                TvShowViewModel(repository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) ->
                DetailViewModel() as T
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}