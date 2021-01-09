package me.farhan.moviecataloq.core.ui.tvshow

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
import me.farhan.moviecataloq.interfaces.TvShowClickListener

/**
 * @author farhan
 * created at at 13:37 on 27/10/2020.
 */
class TvShowAdapter :
  ListAdapter<TvShow, TvShowAdapter.ViewHolder>(DiffCallback()) {

  var listener: TvShowClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false)
    )

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val tvShow = getItem(position)
    tvShow?.let {
      holder.bind(listener, it)
    }
  }

  class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemTvShowBinding.bind(view)
    fun bind(listener: TvShowClickListener?, tvShow: TvShow) {
      view.apply {
        binding.textViewNameTvShowItem.text = tvShow.name
        binding.textViewRatingTvShowItem.text =
          String.format(
            "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
            tvShow.voteAverage,
            tvShow.voteCount
          )
        Glide.with(view)
          .load(BuildConfig.IMAGE_URL + tvShow.cover)
          .apply(
            RequestOptions
              .placeholderOf(R.drawable.ic_baseline_refresh_24)
              .error(R.drawable.ic_baseline_close_24)
          )
          .into(binding.imageViewCoverTvShowItem)
        binding.textViewReleaseDateTvShowItem.text = tvShow.getYear()

        binding.constraintLayoutContainerTvShowItem.setOnClickListener {
          listener?.onItemClicked(view, tvShow)
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