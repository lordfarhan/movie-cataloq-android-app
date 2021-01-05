package me.farhan.moviecataloq.ui.favorite.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_movie.view.*
import me.farhan.moviecataloq.BuildConfig
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.interfaces.MovieClickListener
import me.farhan.moviecataloq.data.model.Movie

/**
 * @author farhan
 * created at at 14:52 on 28/11/20.
 */
class FavoriteMovieAdapter :
  PagedListAdapter<Movie, FavoriteMovieAdapter.ViewHolder>(DiffCallback()) {

  var listener: MovieClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val favMovie = getItem(position)
    favMovie?.let {
      holder.bind(listener, it)
    }
  }

  class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(listener: MovieClickListener?, favoriteMovie: Movie) {
      view.apply {
        textView_titleMovieItem.text = favoriteMovie.title
        textView_ratingMovieItem.text =
          String.format(
            "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
            favoriteMovie.voteAverage,
            favoriteMovie.voteCount
          )
        Glide.with(view)
          .load(BuildConfig.IMAGE_URL + favoriteMovie.cover)
          .apply(
            RequestOptions
              .placeholderOf(R.drawable.ic_baseline_refresh_24)
              .error(R.drawable.ic_baseline_close_24)
          )
          .into(imageView_coverMovieItem)
        textView_releaseDateMovieItem.text = favoriteMovie.getYear()

        constraintLayout_containerMovieItem.setOnClickListener {
          listener?.onItemClicked(view, favoriteMovie)
        }
      }
    }
  }

  class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
      oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
      oldItem.id == newItem.id

  }
}