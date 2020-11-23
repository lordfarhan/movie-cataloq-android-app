package me.farhan.moviecataloq.ui.detail

import androidx.lifecycle.ViewModel
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.model.TvShow

/**
 * @author farhan
 * created at at 11:21 on 02/11/2020.
 */
class DetailViewModel :
    ViewModel() {
    var movie: Movie? = null
    var tvShow: TvShow? = null
}