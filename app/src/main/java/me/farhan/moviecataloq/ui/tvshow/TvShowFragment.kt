package me.farhan.moviecataloq.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_tv_show.*
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.di.ViewModelFactory

/**
 * @author farhan
 * created at at 9:10 on 23/10/2020.
 */
class TvShowFragment : Fragment() {

    private lateinit var viewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter

    companion object {
        fun newInstance(): TvShowFragment {
            val args = Bundle()

            val fragment = TvShowFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            adapter = TvShowAdapter()
            recyclerView_tvShow.adapter = adapter

            subscribeInterface()
        }
    }

    private fun subscribeInterface() {
        viewModel.getTvShows().observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }
}