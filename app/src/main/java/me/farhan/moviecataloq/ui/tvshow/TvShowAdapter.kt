package me.farhan.moviecataloq.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_tv_show.view.*
import me.farhan.moviecataloq.BuildConfig
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.data.model.TvShow
import me.farhan.moviecataloq.ui.detail.DetailActivity

/**
 * @author farhan
 * created at at 13:37 on 27/10/2020.
 */
class TvShowAdapter() :
    ListAdapter<TvShow, TvShowAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = getItem(position)
        holder.bind(tvShow)
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(tvShow: TvShow) {
            view.apply {
                textView_nameTvShowItem.text = tvShow.name
                textView_ratingTvShowItem.text =
                    String.format(
                        "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
                        tvShow.voteAverage,
                        tvShow.voteCount
                    )
                Glide.with(view).load(BuildConfig.IMAGE_URL + tvShow.cover)
                    .into(imageView_coverTvShowItem)
                textView_releaseDateTvShowItem.text = tvShow.getYear()

                constraintLayout_containerTvShowItem.setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra(DetailActivity.TV_SHOW, tvShow)
                    context.startActivity(intent)
                }
            }
        }
    }

    class DiffCallback() : DiffUtil.ItemCallback<TvShow>() {
        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
            oldItem.id == newItem.id

    }
}