package me.farhan.moviecataloq.core.interfaces

import android.view.View
import me.farhan.moviecataloq.core.domain.model.Movie

/**
 * @author farhan
 * created at at 19:48 on 01/12/20.
 */
interface MovieClickListener {
  fun onItemClicked(view: View, movie: Movie)
}