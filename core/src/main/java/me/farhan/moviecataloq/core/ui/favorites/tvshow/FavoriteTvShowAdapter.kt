package me.farhan.moviecataloq.core.ui.favorites.tvshow

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
import me.farhan.moviecataloq.core.databinding.ItemTvShowBinding
import me.farhan.moviecataloq.core.domain.model.TvShow
import me.farhan.moviecataloq.core.util.hide
import me.farhan.moviecataloq.core.util.show
import me.farhan.moviecataloq.core.interfaces.TvShowClickListener

/**
 * @author farhan
 * created at at 16:19 on 28/11/20.
 */
class FavoriteTvShowAdapter :
  ListAdapter<TvShow, FavoriteTvShowAdapter.ViewHolder>(DiffCallback()) {

  var listener: TvShowClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false))

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val favTvShow = getItem(position)
    favTvShow?.let {
      holder.bind(listener, it)
    }
  }

  class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemTvShowBinding.bind(view)
    fun bind(listener: TvShowClickListener?, favoriteTvShow: TvShow) {
      view.apply {
        binding.apply {
          if (favoriteTvShow.isFavorite == 1) {
            imageViewIsTvShowFavoriteItem.show()
          } else {
            imageViewIsTvShowFavoriteItem.hide()
          }
        }
        binding.textViewNameTvShowItem.text = favoriteTvShow.name
        binding.textViewRatingTvShowItem.text =
          String.format(
            "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
            favoriteTvShow.voteAverage,
            favoriteTvShow.voteCount
          )
        Glide.with(view)
          .load(BuildConfig.IMAGE_URL + favoriteTvShow.posterPath)
          .apply(
            RequestOptions
              .placeholderOf(R.drawable.ic_baseline_refresh_24)
              .error(R.drawable.ic_baseline_close_24)
          )
          .into(binding.imageViewCoverTvShowItem)
        binding.textViewReleaseDateTvShowItem.text = favoriteTvShow.getYear()

        binding.constraintLayoutContainerTvShowItem.setOnClickListener {
          listener?.onItemClicked(view, favoriteTvShow)
        }
      }
    }
  }

  class DiffCallback : DiffUtil.ItemCallback<TvShow>() {
    override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
      oldItem == newItem

    override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
      oldItem.id == newItem.id

  }
}