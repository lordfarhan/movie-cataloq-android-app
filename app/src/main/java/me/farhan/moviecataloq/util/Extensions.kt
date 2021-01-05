package me.farhan.moviecataloq.util

import android.content.Context
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText

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