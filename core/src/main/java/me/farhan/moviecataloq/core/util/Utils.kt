package me.farhan.moviecataloq.core.util

import me.farhan.moviecataloq.core.R


/**
 * @author farhan
 * created at at 14:24 on 24/11/20.
 */
fun getMonthFromInt(int: Int): Int {
  return when (int) {
    1 -> R.string.january
    2 -> R.string.february
    3 -> R.string.march
    4 -> R.string.april
    5 -> R.string.may
    6 -> R.string.june
    7 -> R.string.july
    8 -> R.string.august
    9 -> R.string.september
    10 -> R.string.october
    11 -> R.string.november
    12 -> R.string.december
    else -> R.string.january
  }
}