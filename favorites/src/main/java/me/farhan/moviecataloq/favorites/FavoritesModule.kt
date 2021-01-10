package me.farhan.moviecataloq.favorites

import me.farhan.moviecataloq.favorites.movie.FavoriteMovieViewModel
import me.farhan.moviecataloq.favorites.tvshow.FavoriteTvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author farhan
 * created at at 19:21 on 10/01/21.
 */
val favoritesModule = module {
  viewModel { FavoriteMovieViewModel(get()) }
  viewModel { FavoriteTvShowViewModel(get()) }
}