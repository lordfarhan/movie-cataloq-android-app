package me.farhan.moviecataloq.ui.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.farhan.moviecataloq.ui.favorite.movie.FavoriteMovieFragment
import me.farhan.moviecataloq.ui.favorite.tvshow.FavoriteTvShowFragment

/**
 * @author farhan
 * created at at 14:34 on 28/11/20.
 */
class FavoriteAdapter(activity: FavoriteActivity) : FragmentStateAdapter(activity) {
  override fun getItemCount(): Int = 2

  override fun createFragment(position: Int): Fragment {
    return when (position) {
      0 -> FavoriteMovieFragment.newInstance()
      1 -> FavoriteTvShowFragment.newInstance()
      else -> FavoriteMovieFragment.newInstance()
    }
  }

}