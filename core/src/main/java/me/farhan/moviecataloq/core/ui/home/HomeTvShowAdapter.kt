package me.farhan.moviecataloq.core.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.farhan.moviecataloq.core.BuildConfig
import me.farhan.moviecataloq.core.databinding.ItemTvShowPlayingBinding
import me.farhan.moviecataloq.core.domain.model.TvShow
import me.farhan.moviecataloq.core.interfaces.TvShowClickListener

/**
 * @author farhan
 * created at at 14:37 on 01/05/21.
 */
class HomeTvShowAdapter :
  ListAdapter<TvShow, HomeTvShowAdapter.ViewHolder>(DiffCallback()) {

  var listener: TvShowClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(
      ItemTvShowPlayingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val tvShow = getItem(position)
    holder.bind(tvShow, listener)
  }

  class ViewHolder(private val binding: ItemTvShowPlayingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(tvShow: TvShow, listener: TvShowClickListener?) {
      binding.apply {
        textViewNameTvShowItem.text = tvShow.name
        textViewRatingTvShowItem.text =
          String.format("%.1f", tvShow.voteAverage)
        Glide.with(binding.root).load(BuildConfig.IMAGE_URL + tvShow.posterPath)
          .into(imageViewCoverTvShowItem)

        cardViewCardTvShowItem.setOnClickListener {
          listener?.onItemClicked(binding.root, tvShow)
        }
      }
    }
  }

  class DiffCallback : DiffUtil.ItemCallback<TvShow>() {
    override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
      oldItem.id == newItem.id

  }
}