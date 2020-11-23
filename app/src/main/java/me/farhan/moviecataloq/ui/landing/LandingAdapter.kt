package me.farhan.moviecataloq.ui.landing

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.farhan.moviecataloq.ui.movie.MovieFragment
import me.farhan.moviecataloq.ui.tvshow.TvShowFragment

/**
 * @author farhan
 * created at at 9:22 on 23/10/2020.
 */
class LandingAdapter(activity: LandingActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment.newInstance()
            1 -> TvShowFragment.newInstance()
            else -> MovieFragment.newInstance()
        }

}