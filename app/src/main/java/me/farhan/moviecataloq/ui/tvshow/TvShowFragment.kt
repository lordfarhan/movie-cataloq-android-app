package me.farhan.moviecataloq.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_tv_show.*
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.interfaces.TvShowClickListener
import me.farhan.moviecataloq.data.model.TvShow
import me.farhan.moviecataloq.di.ViewModelFactory
import me.farhan.moviecataloq.ui.detail.DetailActivity
import me.farhan.moviecataloq.util.hide
import me.farhan.moviecataloq.util.show
import me.farhan.moviecataloq.vo.Status

/**
 * @author farhan
 * created at at 9:10 on 23/10/2020.
 */
class TvShowFragment : Fragment(), TvShowClickListener {

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
      val factory = ViewModelFactory.getInstance(requireContext())
      viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

      adapter = TvShowAdapter()
      adapter.listener = this
      recyclerView_tvShow.adapter = adapter

      subscribeInterface()
    }
  }

  private fun subscribeInterface() {
    progressBar_tvShow.show()
    viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->
      if (tvShows != null) {
        when (tvShows.status) {
          Status.LOADING -> progressBar_tvShow.show()
          Status.SUCCESS -> {
            progressBar_tvShow.hide()
            adapter.submitList(tvShows.data)
            adapter.notifyDataSetChanged()
          }
          Status.ERROR -> {
            progressBar_tvShow.hide()
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

  override fun onItemClicked(view: View, tvShow: TvShow) {
    val intent = Intent(context, DetailActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
    intent.putExtra(DetailActivity.TV_SHOW, tvShow)
    startActivity(intent)
  }
}