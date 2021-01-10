package me.farhan.moviecataloq.core.ui.favorites.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.farhan.moviecataloq.core.BuildConfig
import me.farhan.moviecataloq.core.R
import me.farhan.moviecataloq.core.databinding.ItemMovieBinding
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.util.hide
import me.farhan.moviecataloq.core.util.show
import me.farhan.moviecataloq.interfaces.MovieClickListener

/**
 * @author farhan
 * created at at 14:52 on 28/11/20.
 */
class FavoriteMovieAdapter :
  ListAdapter<Movie, FavoriteMovieAdapter.ViewHolder>(DiffCallback()) {

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
    private val binding = ItemMovieBinding.bind(view)
    fun bind(listener: MovieClickListener?, favoriteMovie: Movie) {
      view.apply {
        binding.apply {
          if (favoriteMovie.isFavorite == 1) {
            imageViewIsMovieFavoriteItem.show()
          } else {
            imageViewIsMovieFavoriteItem.hide()
          }
        }
        binding.textViewTitleMovieItem.text = favoriteMovie.title
        binding.textViewRatingMovieItem.text =
          String.format(
            "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
            favoriteMovie.voteAverage,
            favoriteMovie.voteCount
          )
        Glide.with(view)
          .load(BuildConfig.IMAGE_URL + favoriteMovie.posterPath)
          .apply(
            RequestOptions
              .placeholderOf(R.drawable.ic_baseline_refresh_24)
              .error(R.drawable.ic_baseline_close_24)
          )
          .into(binding.imageViewCoverMovieItem)
        binding.textViewReleaseDateMovieItem.text = favoriteMovie.getYear()

        binding.constraintLayoutContainerMovieItem.setOnClickListener {
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