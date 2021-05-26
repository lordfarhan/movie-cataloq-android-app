package me.farhan.moviecataloq.core.ui.tvshow

import android.content.Context
import android.view.LayoutInflater
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
 * created at at 13:37 on 27/10/2020.
 */
class TvShowAdapter(private val context: Context) :
  ListAdapter<TvShow, TvShowAdapter.ViewHolder>(DiffCallback()) {

  var listener: TvShowClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false))

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val tvShow = getItem(position)
    tvShow?.let {
      holder.bind(context, listener, it)
    }
  }

  class ViewHolder(private val binding: ItemTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(context: Context, listener: TvShowClickListener?, tvShow: TvShow) {
      binding.apply {
        if (tvShow.isFavorite == 1) {
          imageViewIsTvShowFavoriteItem.show()
        } else {
          imageViewIsTvShowFavoriteItem.hide()
        }
      }
      binding.textViewNameTvShowItem.text = tvShow.name
      binding.textViewRatingTvShowItem.text =
        String.format(
          context.resources.getString(R.string.movie_reviewers),
          tvShow.voteAverage,
          tvShow.voteCount
        )
      Glide.with(binding.root)
        .load(BuildConfig.IMAGE_URL + tvShow.posterPath)
        .apply(
          RequestOptions
            .placeholderOf(R.drawable.ic_baseline_refresh_24)
            .error(R.drawable.ic_baseline_close_24)
        )
        .into(binding.imageViewCoverTvShowItem)
      binding.textViewReleaseDateTvShowItem.text = tvShow.getYear()

      binding.constraintLayoutContainerTvShowItem.setOnClickListener {
        listener?.onItemClicked(binding.root, tvShow)
      }
    }
  }

  class DiffCallback : DiffUtil.ItemCallback<TvShow>() {
    override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
      oldItem.id == newItem.id

  }
}