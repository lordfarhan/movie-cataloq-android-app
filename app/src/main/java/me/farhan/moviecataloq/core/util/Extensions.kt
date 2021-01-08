package me.farhan.moviecataloq.core.util

import android.view.View

/**
 * @author farhan
 * created at at 14:00 on 24/11/20.
 */
fun View.show() {
  visibility = View.VISIBLE
}

fun View.hide() {
  visibility = View.GONE
}