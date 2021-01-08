package me.farhan.moviecataloq.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_movie.*
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.util.hide
import me.farhan.moviecataloq.core.util.show
import me.farhan.moviecataloq.interfaces.MovieClickListener
import me.farhan.moviecataloq.ui.detail.DetailActivity
import me.farhan.moviecataloq.vo.Resource
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * @author farhan
 * created at at 9:09 on 23/10/2020.
 */
class MovieFragment : Fragment(), MovieClickListener {

  private val viewModel: MovieViewModel by viewModel()
  private lateinit var adapter: MovieAdapter

  companion object {
    fun newInstance(): MovieFragment {
      val args = Bundle()

      val fragment = MovieFragment()
      fragment.arguments = args
      return fragment
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_movie, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    if (activity != null) {
      adapter = MovieAdapter()
      adapter.listener = this
      recyclerView_movie.adapter = adapter

      subscribeInterface()
    }
  }

  private fun subscribeInterface() {
    progressBar_movie.show()
    viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
      if (movies != null) {
        when (movies) {
          is Resource.Loading -> {
            progressBar_movie.show()
          }
          is Resource.Success -> {
            progressBar_movie.hide()
            adapter.submitList(movies.data)
            adapter.notifyDataSetChanged()
          }
          is Resource.Error -> {
            progressBar_movie.hide()
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
    val intent = Intent(context, DetailActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
    intent.putExtra(DetailActivity.MOVIE, movie)
    startActivity(intent)
  }
}