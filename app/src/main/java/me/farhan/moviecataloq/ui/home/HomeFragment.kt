package me.farhan.moviecataloq.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.core.data.Resource
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.domain.model.TvShow
import me.farhan.moviecataloq.core.interfaces.MovieClickListener
import me.farhan.moviecataloq.core.interfaces.TvShowClickListener
import me.farhan.moviecataloq.core.ui.home.HomeMovieAdapter
import me.farhan.moviecataloq.core.ui.home.HomeTvShowAdapter
import me.farhan.moviecataloq.core.util.invisible
import me.farhan.moviecataloq.core.util.show
import me.farhan.moviecataloq.databinding.FragmentHomeBinding
import me.farhan.moviecataloq.ui.detail.DetailActivity
import me.farhan.moviecataloq.util.RecyclerViewItemDecorator
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt

/**
 * @author farhan
 * created at at 11:08 on 01/05/21.
 */
class HomeFragment : Fragment(), MovieClickListener, TvShowClickListener {
  private val viewModel: HomeViewModel by viewModel()
  private lateinit var movieAdapter: HomeMovieAdapter
  private lateinit var tvShowAdapter: HomeTvShowAdapter
  private var _binding: FragmentHomeBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if (activity != null) {
      val edge = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, 8F, resources.displayMetrics
      ).roundToInt()

      movieAdapter = HomeMovieAdapter()
      movieAdapter.listener = this

      tvShowAdapter = HomeTvShowAdapter()
      tvShowAdapter.listener = this

      binding.recyclerViewPopularMovies.adapter = movieAdapter
      binding.recyclerViewPopularMovies.addItemDecoration(RecyclerViewItemDecorator(edge))

      binding.recyclerViewPopularTvShows.adapter = tvShowAdapter
      binding.recyclerViewPopularTvShows.addItemDecoration(RecyclerViewItemDecorator(edge))

      requestData()
    }
  }

  private fun requestData() {
    viewModel.getNowPlayingMovies().observe(viewLifecycleOwner, { movies ->
      if (movies != null) {
        when (movies) {
          is Resource.Loading -> {
            binding.linearLayoutShimmerMovie.show()
          }
          is Resource.Success -> {
            binding.linearLayoutShimmerMovie.invisible()
            movieAdapter.submitList(movies.data)
            movieAdapter.notifyDataSetChanged()
          }
          is Resource.Error -> {
            binding.linearLayoutShimmerMovie.invisible()
            Toast.makeText(
              requireContext(),
              resources.getString(R.string.dialog_error),
              Toast.LENGTH_SHORT
            ).show()
          }
        }
      }
    })
    viewModel.getNowPlayingTvShows().observe(viewLifecycleOwner, { tvShows ->
      if (tvShows != null) {
        when (tvShows) {
          is Resource.Loading -> {
            binding.linearLayoutShimmerTvShow.show()
          }
          is Resource.Success -> {
            binding.linearLayoutShimmerTvShow.invisible()
            tvShowAdapter.submitList(tvShows.data)
            tvShowAdapter.notifyDataSetChanged()
          }
          is Resource.Error -> {
            binding.linearLayoutShimmerTvShow.invisible()
            Toast.makeText(
              requireContext(),
              resources.getString(R.string.dialog_error),
              Toast.LENGTH_SHORT
            ).show()
          }
        }
      }
    })
  }

  override fun onItemClicked(view: View, movie: Movie) {
    val intent = Intent(requireContext(), DetailActivity::class.java)
    intent.putExtra(DetailActivity.MOVIE, movie)
    requireContext().startActivity(intent)
  }

  override fun onItemClicked(view: View, tvShow: TvShow) {
    val intent = Intent(requireContext(), DetailActivity::class.java)
    intent.putExtra(DetailActivity.TV_SHOW, tvShow)
    requireContext().startActivity(intent)
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }
}