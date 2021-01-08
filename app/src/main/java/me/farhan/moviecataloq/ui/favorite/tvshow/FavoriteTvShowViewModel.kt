package me.farhan.moviecataloq.ui.favorite.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import me.farhan.moviecataloq.core.domain.usecase.UseCase

/**
 * @author farhan
 * created at at 16:19 on 28/11/20.
 */
class FavoriteTvShowViewModel(private val useCase: UseCase) : ViewModel() {
  fun getFavoriteTvShows() = useCase.getFavoriteTvShows().asLiveData()
}
