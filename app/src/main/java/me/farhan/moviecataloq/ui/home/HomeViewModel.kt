package me.farhan.moviecataloq.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import me.farhan.moviecataloq.core.domain.usecase.UseCase

/**
 * @author farhan
 * created at at 12:01 on 01/05/21.
 */
class HomeViewModel constructor(private val useCase: UseCase) : ViewModel() {
  fun getNowPlayingMovies() = useCase.getMovies().asLiveData()

  fun getNowPlayingTvShows() = useCase.getTvShows().asLiveData()
}