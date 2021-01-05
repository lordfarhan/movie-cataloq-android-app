package me.farhan.moviecataloq.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*
import me.farhan.moviecataloq.BuildConfig
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.model.TvShow
import me.farhan.moviecataloq.di.ViewModelFactory
import me.farhan.moviecataloq.util.hide
import me.farhan.moviecataloq.util.show
import me.farhan.moviecataloq.vo.Status

/**
 * @author farhan
 * created at at 9:13 on 23/10/2020.
 */
class DetailActivity : AppCompatActivity() {

  companion object {
    const val MOVIE = "movie"
    const val TV_SHOW = "tv_show"
  }

  private lateinit var mMovie: Movie
  private lateinit var mTvShow: TvShow

  private lateinit var viewModel: DetailViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)

    imageView_actionBack.setOnClickListener { onBackPressed() }

    val factory = ViewModelFactory.getInstance(this)

    viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

    when {
      intent.hasExtra(MOVIE) -> {
        mMovie = intent.getParcelableExtra(MOVIE) as Movie
        viewModel.setMovieId(mMovie.id)
        supportActionBar?.setTitle(R.string.movie)
        subscribeMovieDetail()
      }
      intent.hasExtra(TV_SHOW) -> {
        mTvShow = intent.getParcelableExtra(TV_SHOW) as TvShow
        viewModel.setTvShowId(mTvShow.id)
        supportActionBar?.setTitle(R.string.tv_show)
        subscribeTvShowDetail()
      }
      else -> {
        finish()
      }
    }
  }

  private fun initialFavoriteFab(isFavorite: Boolean) {
    if (isFavorite) {
      floatingActionButton_favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
      floatingActionButton_favorite.setOnClickListener { removeFromFavorite() }
    } else {
      floatingActionButton_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
      floatingActionButton_favorite.setOnClickListener { addToFavorite() }
    }
  }

  private fun addToFavorite() {
    viewModel.addFavorite()
    floatingActionButton_favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
  }

  private fun removeFromFavorite() {
    viewModel.removeFavorite()
    floatingActionButton_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
  }

  private fun subscribeMovieDetail() {
    progressBar_detail.show()
    viewModel.getMovie().observe(this, { movie ->
      if (movie != null) {
        movie.data?.let { viewModel.setMovie(it) }
        when (movie.status) {
          Status.LOADING -> progressBar_detail.show()
          Status.SUCCESS -> {
            progressBar_detail.hide()
            Glide.with(this)
              .load(BuildConfig.IMAGE_URL + movie.data?.cover)
              .apply(
                RequestOptions
                  .placeholderOf(R.drawable.ic_baseline_refresh_24)
                  .error(R.drawable.ic_baseline_close_24)
              )
              .into(imageView_backCoverDetail)
            Glide.with(this)
              .load(BuildConfig.IMAGE_URL + movie.data?.cover)
              .apply(
                RequestOptions
                  .placeholderOf(R.drawable.ic_baseline_refresh_24)
                  .error(R.drawable.ic_baseline_close_24)
              )
              .into(imageView_coverDetail)
            textView_titleDetail.text = movie.data?.title
            textView_taglineDetail.text = movie.data?.tagline
            textView_ratingDetail.text =
              String.format(
                "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
                movie.data?.voteAverage,
                movie.data?.voteCount
              )
            textView_dateTitleDetail.text = resources.getString(R.string.movie_release_date)
            textView_dateDetail.text = movie.data?.getDate()
            textView_statusDetail.text = movie.data?.status
            textView_overviewDetail.text = movie.data?.overview
            initialFavoriteFab(viewModel.isFavorite(movie.data?.isFavorite))
          }
          Status.ERROR -> {
            progressBar_detail.hide()
            Toast.makeText(
              this,
              resources.getString(R.string.dialog_error),
              Toast.LENGTH_SHORT
            ).show()
          }
        }
      }
    })
  }

  private fun subscribeTvShowDetail() {
    progressBar_detail.show()
    viewModel.getTvShow().observe(this, { tvShow ->
      if (tvShow != null) {
        tvShow.data?.let { viewModel.setTvShow(it) }
        when (tvShow.status) {
          Status.LOADING -> progressBar_detail.show()
          Status.SUCCESS -> {
            progressBar_detail.hide()
            Glide.with(this)
              .load(BuildConfig.IMAGE_URL + tvShow.data?.cover)
              .apply(
                RequestOptions
                  .placeholderOf(R.drawable.ic_baseline_refresh_24)
                  .error(R.drawable.ic_baseline_close_24)
              )
              .into(imageView_backCoverDetail)
            Glide.with(this)
              .load(BuildConfig.IMAGE_URL + tvShow.data?.cover)
              .apply(
                RequestOptions
                  .placeholderOf(R.drawable.ic_baseline_refresh_24)
                  .error(R.drawable.ic_baseline_close_24)
              )
              .into(imageView_coverDetail)
            textView_titleDetail.text = tvShow.data?.name
            textView_taglineDetail.text = tvShow.data?.tagline
            textView_ratingDetail.text =
              String.format(
                "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
                tvShow.data?.voteAverage,
                tvShow.data?.voteCount
              )
            textView_dateTitleDetail.text = resources.getString(R.string.tv_show_first_air_date)
            textView_dateDetail.text = tvShow.data?.getDate()
            textView_statusDetail.text = tvShow.data?.status
            textView_overviewDetail.text = tvShow.data?.overview
            initialFavoriteFab(viewModel.isFavorite(tvShow.data?.isFavorite))
          }
          Status.ERROR -> {
            progressBar_detail.hide()
            Toast.makeText(
              this,
              resources.getString(R.string.dialog_error),
              Toast.LENGTH_SHORT
            ).show()
          }
        }
      }
    })
  }
}