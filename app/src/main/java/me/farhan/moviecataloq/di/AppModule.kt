package me.farhan.moviecataloq.di

import me.farhan.moviecataloq.core.domain.usecase.Interactor
import me.farhan.moviecataloq.core.domain.usecase.UseCase
import me.farhan.moviecataloq.ui.detail.DetailViewModel
import me.farhan.moviecataloq.ui.home.HomeViewModel
import me.farhan.moviecataloq.ui.movie.MovieViewModel
import me.farhan.moviecataloq.ui.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author farhan
 * created at at 10:02 on 05/01/21.
 */
val useCaseModule = module {
  factory<UseCase> { Interactor(get()) }
}

val viewModelModule = module {
  viewModel { HomeViewModel(get()) }
  viewModel { MovieViewModel(get()) }
  viewModel { TvShowViewModel(get()) }
  viewModel { DetailViewModel(get()) }
}