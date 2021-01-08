package me.farhan.moviecataloq.interfaces

import android.view.View
import me.farhan.moviecataloq.core.domain.model.TvShow

/**
 * @author farhan
 * created at at 19:49 on 01/12/20.
 */
interface TvShowClickListener {
  fun onItemClicked(view: View, tvShow: TvShow)
}