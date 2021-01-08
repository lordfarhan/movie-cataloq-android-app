package me.farhan.moviecataloq.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import me.farhan.moviecataloq.core.domain.usecase.UseCase

/**
 * @author farhan
 * created at at 13:52 on 23/10/2020.
 */
class MovieViewModel(private val useCase: UseCase) : ViewModel() {
  fun getMovies() = useCase.getMovies().asLiveData()
}
