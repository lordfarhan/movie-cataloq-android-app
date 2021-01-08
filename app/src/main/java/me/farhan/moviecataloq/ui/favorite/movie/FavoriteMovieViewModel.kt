package me.farhan.moviecataloq.ui.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import me.farhan.moviecataloq.core.domain.usecase.UseCase

/**
 * @author farhan
 * created at at 14:52 on 28/11/20.
 */
class FavoriteMovieViewModel(private val useCase: UseCase) : ViewModel() {
  fun getFavoriteMovies() = useCase.getFavoriteMovies().asLiveData()
}
