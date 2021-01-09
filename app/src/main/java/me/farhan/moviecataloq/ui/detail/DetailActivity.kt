package me.farhan.moviecataloq.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.farhan.moviecataloq.BuildConfig
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.domain.model.TvShow
import me.farhan.moviecataloq.core.util.hide
import me.farhan.moviecataloq.core.util.show
import me.farhan.moviecataloq.databinding.ActivityDetailBinding
import me.farhan.moviecataloq.vo.Resource
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * @author farhan
 * created at at 9:13 on 23/10/2020.
 */
class DetailActivity : AppCompatActivity() {

  companion object {
    const val MOVIE = "movie"
    const val TV_SHOW = "tv_show"
  }

  private lateinit var binding: ActivityDetailBinding
  private lateinit var mMovie: Movie
  private lateinit var mTvShow: TvShow

  private val viewModel: DetailViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

    binding.imageViewActionBack.setOnClickListener { onBackPressed() }

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
    binding.apply {
      if (isFavorite) {
        floatingActionButtonFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        floatingActionButtonFavorite.setOnClickListener { removeFromFavorite() }
      } else {
        floatingActionButtonFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        floatingActionButtonFavorite.setOnClickListener { addToFavorite() }
      }
    }
  }

  private fun addToFavorite() {
    viewModel.addFavorite()
    binding.floatingActionButtonFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
  }

  private fun removeFromFavorite() {
    viewModel.removeFavorite()
    binding.floatingActionButtonFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
  }

  private fun subscribeMovieDetail() {
    binding.progressBarDetail.show()
    viewModel.getMovie().observe(this, { movie ->
      if (movie != null) {
        movie.data?.let { viewModel.setMovie(it) }
        when (movie) {
          is Resource.Loading -> {
            binding.progressBarDetail.show()
          }
          is Resource.Success -> {
            binding.progressBarDetail.hide()
            Glide.with(this)
              .load(BuildConfig.IMAGE_URL + movie.data?.cover)
              .apply(
                RequestOptions
                  .placeholderOf(R.drawable.ic_baseline_refresh_24)
                  .error(R.drawable.ic_baseline_close_24)
              )
              .into(binding.imageViewBackCoverDetail)
            Glide.with(this)
              .load(BuildConfig.IMAGE_URL + movie.data?.cover)
              .apply(
                RequestOptions
                  .placeholderOf(R.drawable.ic_baseline_refresh_24)
                  .error(R.drawable.ic_baseline_close_24)
              )
              .into(binding.imageViewCoverDetail)
            binding.apply {
              textViewTitleDetail.text = movie.data?.title
              textViewTaglineDetail.text = movie.data?.tagline
              textViewRatingDetail.text =
                String.format(
                  "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
                  movie.data?.voteAverage,
                  movie.data?.voteCount
                )
              textViewDateTitleDetail.text = resources.getString(R.string.movie_release_date)
              textViewDateDetail.text = movie.data?.getDate()
              textViewStatusDetail.text = movie.data?.status
              textViewOverviewDetail.text = movie.data?.overview
            }
            initialFavoriteFab(viewModel.isFavorite(movie.data?.isFavorite))
          }
          is Resource.Error -> {
            binding.progressBarDetail.hide()
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
    binding.progressBarDetail.show()
    viewModel.getTvShow().observe(this, { tvShow ->
      if (tvShow != null) {
        tvShow.data?.let { viewModel.setTvShow(it) }
        when (tvShow) {
          is Resource.Loading -> {
            binding.progressBarDetail.show()
          }
          is Resource.Success -> {
            binding.progressBarDetail.hide()
            Glide.with(this)
              .load(BuildConfig.IMAGE_URL + tvShow.data?.cover)
              .apply(
                RequestOptions
                  .placeholderOf(R.drawable.ic_baseline_refresh_24)
                  .error(R.drawable.ic_baseline_close_24)
              )
              .into(binding.imageViewBackCoverDetail)
            Glide.with(this)
              .load(BuildConfig.IMAGE_URL + tvShow.data?.cover)
              .apply(
                RequestOptions
                  .placeholderOf(R.drawable.ic_baseline_refresh_24)
                  .error(R.drawable.ic_baseline_close_24)
              )
              .into(binding.imageViewCoverDetail)
            binding.apply {
              textViewTitleDetail.text = tvShow.data?.name
              textViewTaglineDetail.text = tvShow.data?.tagline
              textViewRatingDetail.text =
                String.format(
                  "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
                  tvShow.data?.voteAverage,
                  tvShow.data?.voteCount
                )
              textViewDateTitleDetail.text = resources.getString(R.string.tv_show_first_air_date)
              textViewDateDetail.text = tvShow.data?.getDate()
              textViewStatusDetail.text = tvShow.data?.status
              textViewOverviewDetail.text = tvShow.data?.overview
            }
            initialFavoriteFab(viewModel.isFavorite(tvShow.data?.isFavorite))
          }
          is Resource.Error -> {
            binding.progressBarDetail.hide()
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