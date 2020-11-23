package me.farhan.moviecataloq.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import me.farhan.moviecataloq.BuildConfig
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.model.TvShow
import me.farhan.moviecataloq.di.ViewModelFactory

/**
 * @author farhan
 * created at at 9:13 on 23/10/2020.
 */
class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tv_show"
    }

    private lateinit var movie: Movie
    private lateinit var tvShow: TvShow

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar_detail)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()

        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        when {
            intent.hasExtra(MOVIE) -> {
                movie = intent.getParcelableExtra(MOVIE) as Movie
                viewModel.movie = movie
                supportActionBar?.setTitle(R.string.movie)
                subscribeMovieDetail()
            }
            intent.hasExtra(TV_SHOW) -> {
                tvShow = intent.getParcelableExtra(TV_SHOW) as TvShow
                viewModel.tvShow = tvShow
                supportActionBar?.setTitle(R.string.tv_show)
                subscribeTvShowDetail()
            }
            else -> {
                finish()
            }
        }
    }

    private fun subscribeMovieDetail() {
        Glide.with(this).load(BuildConfig.IMAGE_URL + viewModel.movie?.cover)
            .into(imageView_coverDetail)
        textView_titleDetail.text = viewModel.movie?.title
        textView_ratingDetail.text =
            String.format(
                "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
                viewModel.movie?.voteAverage,
                viewModel.movie?.voteCount
            )
        textView_overviewDetail.text = viewModel.movie?.overview
    }

    private fun subscribeTvShowDetail() {
        Glide.with(this).load(BuildConfig.IMAGE_URL + viewModel.tvShow?.cover)
            .into(imageView_coverDetail)
        textView_titleDetail.text = viewModel.tvShow?.name
        textView_ratingDetail.text =
            String.format(
                "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
                viewModel.tvShow?.voteAverage,
                viewModel.tvShow?.voteCount
            )
        textView_overviewDetail.text = viewModel.tvShow?.overview
    }

}