package me.farhan.moviecataloq.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_movie.*
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.di.ViewModelFactory

/**
 * @author farhan
 * created at at 9:09 on 23/10/2020.
 */
class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
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
            val factory = ViewModelFactory.getInstance()
            viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            adapter = MovieAdapter()
            recyclerView_movie.adapter = adapter

            subscribeInterface()
        }
    }

    private fun subscribeInterface() {
        viewModel.getMovies().observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }
}