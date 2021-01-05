package me.farhan.moviecataloq.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.interfaces.MovieClickListener
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.di.ViewModelFactory
import me.farhan.moviecataloq.ui.detail.DetailActivity
import me.farhan.moviecataloq.util.hide
import me.farhan.moviecataloq.util.show

/**
 * @author farhan
 * created at at 14:28 on 28/11/20.
 */
class FavoriteMovieFragment : Fragment(), MovieClickListener {

  private lateinit var viewModel: FavoriteMovieViewModel
  private lateinit var adapter: FavoriteMovieAdapter

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
  ): View? {
    return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if (activity != null) {
      val factory = ViewModelFactory.getInstance(requireContext())
      viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

      adapter = FavoriteMovieAdapter()
      adapter.listener = this
      recyclerView_favoriteMovie.adapter = adapter

      subscribeInterface()
    }

  }

  private fun subscribeInterface() {
    progressBar_favoriteMovie.show()
    viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { favoriteMovies ->
      if (favoriteMovies != null) {
        progressBar_favoriteMovie.hide()
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