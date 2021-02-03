package me.farhan.moviecataloq.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_tv_show.*
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.core.data.Resource
import me.farhan.moviecataloq.core.domain.model.TvShow
import me.farhan.moviecataloq.core.ui.tvshow.TvShowAdapter
import me.farhan.moviecataloq.core.util.hide
import me.farhan.moviecataloq.core.util.show
import me.farhan.moviecataloq.databinding.FragmentTvShowBinding
import me.farhan.moviecataloq.interfaces.TvShowClickListener
import me.farhan.moviecataloq.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * @author farhan
 * created at at 9:10 on 23/10/2020.
 */
class TvShowFragment : Fragment(), TvShowClickListener {

  private val viewModel: TvShowViewModel by viewModel()
  private var _binding: FragmentTvShowBinding? = null
  private val binding get() = _binding!!

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
  ): View {
    _binding = FragmentTvShowBinding.inflate(inflater, container, false)
    context ?: return binding.root
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    if (activity != null) {
      val adapter = TvShowAdapter()
      adapter.listener = this
      recyclerView_tvShow.adapter = adapter

      subscribeInterface(adapter)
    }
  }

  private fun subscribeInterface(adapter: TvShowAdapter) {
    progressBar_tvShow.show()
    viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->
      if (tvShows != null) {
        when (tvShows) {
          is Resource.Loading -> {
            progressBar_tvShow.show()
          }
          is Resource.Success -> {
            progressBar_tvShow.hide()
            adapter.submitList(tvShows.data)
            adapter.notifyDataSetChanged()
          }
          is Resource.Error -> {
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

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}