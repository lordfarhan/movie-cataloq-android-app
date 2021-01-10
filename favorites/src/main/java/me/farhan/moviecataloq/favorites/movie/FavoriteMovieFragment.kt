package me.farhan.moviecataloq.favorites.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.ui.favorite.movie.FavoriteMovieAdapter
import me.farhan.moviecataloq.core.util.hide
import me.farhan.moviecataloq.core.util.show
import me.farhan.moviecataloq.databinding.FragmentFavoriteMovieBinding
import me.farhan.moviecataloq.interfaces.MovieClickListener
import me.farhan.moviecataloq.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * @author farhan
 * created at at 14:28 on 28/11/20.
 */
class FavoriteMovieFragment : Fragment(), MovieClickListener {

  private val viewModel: FavoriteMovieViewModel by viewModel()
  private lateinit var adapter: FavoriteMovieAdapter
  private lateinit var binding: FragmentFavoriteMovieBinding

  companion object {
    fun newInstance(): Fragment {
      val args = Bundle()

      val fragment = FavoriteMovieFragment()
      fragment.arguments = args
      return fragment
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
    context ?: return binding.root
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if (activity != null) {
      adapter = FavoriteMovieAdapter()
      adapter.listener = this
      binding.recyclerViewFavoriteMovie.adapter = adapter

      subscribeInterface()
    }
  }

  private fun subscribeInterface() {
    binding.progressBarFavoriteMovie.show()
    viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { favoriteMovies ->
      if (favoriteMovies != null) {
        binding.progressBarFavoriteMovie.hide()
        adapter.submitList(favoriteMovies)
        adapter.notifyDataSetChanged()
      }
    })
  }

  override fun onItemClicked(view: View, movie: Movie) {
    val intent = Intent(context, DetailActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
    intent.putExtra(DetailActivity.MOVIE, movie)
    startActivity(intent)
  }
}