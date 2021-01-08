package me.farhan.moviecataloq.core.util

import me.farhan.moviecataloq.App
import me.farhan.moviecataloq.R

/**
 * @author farhan
 * created at at 14:24 on 24/11/20.
 */
fun getMonthFromInt(int: Int): String {
    return when (int) {
        1 -> App.getContext().resources.getString(R.string.january)
        2 -> App.getContext().resources.getString(R.string.february)
        3 -> App.getContext().resources.getString(R.string.march)
        4 -> App.getContext().resources.getString(R.string.april)
        5 -> App.getContext().resources.getString(R.string.may)
        6 -> App.getContext().resources.getString(R.string.june)
        7 -> App.getContext().resources.getString(R.string.july)
        8 -> App.getContext().resources.getString(R.string.august)
        9 -> App.getContext().resources.getString(R.string.september)
        10 -> App.getContext().resources.getString(R.string.october)
        11 -> App.getContext().resources.getString(R.string.november)
        12 -> App.getContext().resources.getString(R.string.december)
        else -> ""
    }
}