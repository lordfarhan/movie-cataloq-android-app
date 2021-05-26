package me.farhan.moviecataloq.ui.detail

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import me.farhan.moviecataloq.BuildConfig
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.core.data.Resource
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.domain.model.TvShow
import me.farhan.moviecataloq.core.util.hide
import me.farhan.moviecataloq.core.util.show
import me.farhan.moviecataloq.databinding.ActivityDetailBinding
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
  private lateinit var movie: Movie
  private lateinit var tvShow: TvShow

  private val viewModel: DetailViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityDetailBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setSupportActionBar(binding.toolbarDetail)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = " "

    when {
      intent.hasExtra(MOVIE) -> {
        movie = intent.getParcelableExtra<Movie>(MOVIE) as Movie
        viewModel.setMovieId(movie.id)
        setCollapsingToolbarLayout(movie.title)
        subscribeMovieDetail()
      }
      intent.hasExtra(TV_SHOW) -> {
        tvShow = intent.getParcelableExtra<TvShow>(TV_SHOW) as TvShow
        viewModel.setTvShowId(tvShow.id)
        setCollapsingToolbarLayout(tvShow.name)
        subscribeTvShowDetail()
      }
      else -> {
        finish()
      }
    }
  }

  private fun setCollapsingToolbarLayout(title: String) {
    var isShow = true
    var scrollRange = -1
    binding.apply {
      collapsingToolbarLayoutDetail.collapsedTitleGravity = Gravity.START
      collapsingToolbarLayoutDetail.setCollapsedTitleTextColor(
        ContextCompat.getColor(
          this@DetailActivity,
          R.color.white
        )
      )

      appBarLayoutDetail.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
        if (scrollRange == -1) {
          scrollRange = barLayout?.totalScrollRange!!
        }
        if (scrollRange + verticalOffset == 0) {
          collapsingToolbarLayoutDetail.title = title
          toolbarDetail.setBackgroundColor(
            ContextCompat.getColor(
              this@DetailActivity,
              R.color.primary
            )
          )
          isShow = true
        } else if (isShow) {
          collapsingToolbarLayoutDetail.title = " "
          toolbarDetail.setBackgroundColor(
            ContextCompat.getColor(
              this@DetailActivity,
              android.R.color.transparent
            )
          )
          isShow = false
        }
      })
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
              .load(BuildConfig.IMAGE_URL + movie.data?.backdropPath)
              .apply(
                RequestOptions
                  .placeholderOf(R.drawable.ic_baseline_refresh_24)
                  .error(R.drawable.ic_baseline_close_24)
              )
              .into(binding.imageViewBackCoverDetail)
            Glide.with(this)
              .load(BuildConfig.IMAGE_URL + movie.data?.posterPath)
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
              textViewTitleDetail.text = movie.data?.title
              textViewTaglineDetail.text = movie.data?.tagline
              textViewRatingDetail.text =
                String.format(
                  "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
                  movie.data?.voteAverage,
                  movie.data?.voteCount
                )
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
              .load(BuildConfig.IMAGE_URL + tvShow.data?.backdropPath)
              .apply(
                RequestOptions
                  .placeholderOf(R.drawable.ic_baseline_refresh_24)
                  .error(R.drawable.ic_baseline_close_24)
              )
              .into(binding.imageViewBackCoverDetail)
            Glide.with(this)
              .load(BuildConfig.IMAGE_URL + tvShow.data?.posterPath)
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
              textViewTitleDetail.text = tvShow.data?.name
              textViewTaglineDetail.text = tvShow.data?.tagline
              textViewRatingDetail.text =
                String.format(
                  "%.1f (%d ${resources.getString(R.string.movie_reviewers)})",
                  tvShow.data?.voteAverage,
                  tvShow.data?.voteCount
                )
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