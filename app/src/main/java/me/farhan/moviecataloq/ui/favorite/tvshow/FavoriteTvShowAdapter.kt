package me.farhan.moviecataloq.ui.favorite.tvshow

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
 * created at at 16:19 on 28/11/20.
 */
class FavoriteTvShowAdapter :
  PagedListAdapter<TvShow, FavoriteTvShowAdapter.ViewHolder>(DiffCallback()) {

  var listener: TvShowClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false)
    )

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val favTvShow = getItem(position)
    favTvShow?.let {
      holder.bind(listener, it)
    }
  }

  class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(listener: TvShowClickListener?, favoriteTvShow: TvShow) {
      view.apply {
        textView_nameTvShowItem.text = favoriteTvShow.name
        textView_ratingTvShowItem.text =
          String.format(
            "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
            favoriteTvShow.voteAverage,
            favoriteTvShow.voteCount
          )
        Glide.with(view)
          .load(BuildConfig.IMAGE_URL + favoriteTvShow.cover)
          .apply(
            RequestOptions
              .placeholderOf(R.drawable.ic_baseline_refresh_24)
              .error(R.drawable.ic_baseline_close_24)
          )
          .into(imageView_coverTvShowItem)
        textView_releaseDateTvShowItem.text = favoriteTvShow.getYear()

        constraintLayout_containerTvShowItem.setOnClickListener {
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