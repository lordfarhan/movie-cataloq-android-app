package me.farhan.moviecataloq.ui.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_tv_show.view.*
import me.farhan.moviecataloq.BuildConfig
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.interfaces.TvShowClickListener
import me.farhan.moviecataloq.data.model.TvShow

/**
 * @author farhan
 * created at at 13:37 on 27/10/2020.
 */
class TvShowAdapter :
  PagedListAdapter<TvShow, TvShowAdapter.ViewHolder>(DiffCallback()) {

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
    fun bind(listener: TvShowClickListener?, tvShow: TvShow) {
      view.apply {
        textView_nameTvShowItem.text = tvShow.name
        textView_ratingTvShowItem.text =
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
          .into(imageView_coverTvShowItem)
        textView_releaseDateTvShowItem.text = tvShow.getYear()

        constraintLayout_containerTvShowItem.setOnClickListener {
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