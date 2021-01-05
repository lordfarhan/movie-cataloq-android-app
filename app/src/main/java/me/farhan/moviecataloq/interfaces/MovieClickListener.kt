package me.farhan.moviecataloq.interfaces

import android.view.View
import me.farhan.moviecataloq.data.model.Movie

/**
 * @author farhan
 * created at at 19:48 on 01/12/20.
 */
interface MovieClickListener {
  fun onItemClicked(view: View, movie: Movie)
}