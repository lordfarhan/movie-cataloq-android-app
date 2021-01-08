package me.farhan.moviecataloq.ui.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import me.farhan.moviecataloq.core.domain.usecase.UseCase

/**
 * @author farhan
 * created at at 13:40 on 27/10/2020.
 */
class TvShowViewModel(private val useCase: UseCase) : ViewModel() {
  fun getTvShows() = useCase.getTvShows().asLiveData()
}