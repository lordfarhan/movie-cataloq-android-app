package me.farhan.moviecataloq.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_movie.view.*
import me.farhan.moviecataloq.BuildConfig
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.interfaces.MovieClickListener

/**
 * @author farhan
 * created at at 14:38 on 23/10/2020.
 */
class MovieAdapter :
  ListAdapter<Movie, MovieAdapter.ViewHolder>(DiffCallback()) {

  var listener: MovieClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val movie = getItem(position)
    movie?.let {
      holder.bind(listener, it)
    }
  }

  class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(listener: MovieClickListener?, movie: Movie) {
      view.apply {
        textView_titleMovieItem.text = movie.title
        textView_ratingMovieItem.text =
          String.format(
            "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
            movie.voteAverage,
            movie.voteCount
          )
        Glide.with(view)
          .load(BuildConfig.IMAGE_URL + movie.cover)
          .apply(
            RequestOptions
              .placeholderOf(R.drawable.ic_baseline_refresh_24)
              .error(R.drawable.ic_baseline_close_24)
          )
          .into(imageView_coverMovieItem)
        textView_releaseDateMovieItem.text = movie.getYear()

        constraintLayout_containerMovieItem.setOnClickListener {
          listener?.onItemClicked(view, movie)
        }
      }
    }
  }

  class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
      oldItem.id == newItem.id

  }
}