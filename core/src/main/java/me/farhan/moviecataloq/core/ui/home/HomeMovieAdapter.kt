package me.farhan.moviecataloq.core.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.farhan.moviecataloq.core.BuildConfig
import me.farhan.moviecataloq.core.databinding.ItemMoviePlayingBinding
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.interfaces.MovieClickListener

/**
 * @author farhan
 * created at at 13:32 on 01/05/21.
 */
class HomeMovieAdapter :
  ListAdapter<Movie, HomeMovieAdapter.ViewHolder>(DiffCallback()) {

  var listener: MovieClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(
      ItemMoviePlayingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val movie = getItem(position)
    holder.bind(movie, listener)
  }

  class ViewHolder(private val binding: ItemMoviePlayingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie, listener: MovieClickListener?) {
      binding.apply {
        textViewTitleMovieItem.text = movie.title
        textViewRatingMovieItem.text =
          String.format("%.1f", movie.voteAverage)
        Glide.with(binding.root).load(BuildConfig.IMAGE_URL + movie.posterPath)
          .into(imageViewCoverMovieItem)

        cardViewCardMovieItem.setOnClickListener {
          listener?.onItemClicked(binding.root, movie)
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