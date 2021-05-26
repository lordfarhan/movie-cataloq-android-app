package me.farhan.moviecataloq.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.core.data.Resource
import me.farhan.moviecataloq.core.domain.model.TvShow
import me.farhan.moviecataloq.core.ui.tvshow.TvShowAdapter
import me.farhan.moviecataloq.core.util.hide
import me.farhan.moviecataloq.core.util.show
import me.farhan.moviecataloq.databinding.FragmentTvShowBinding
import me.farhan.moviecataloq.core.interfaces.TvShowClickListener
import me.farhan.moviecataloq.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * @author farhan
 * created at at 9:10 on 23/10/2020.
 */
class TvShowFragment : Fragment(), TvShowClickListener {

  private val viewModel: TvShowViewModel by viewModel()
  private lateinit var adapter: TvShowAdapter
  private lateinit var binding: FragmentTvShowBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentTvShowBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if (activity != null) {
      adapter = TvShowAdapter(requireContext())
      adapter.listener = this
      binding.recyclerViewTvShow.adapter = adapter

      subscribeInterface()
    }
  }

  private fun subscribeInterface() {
    binding.progressBarTvShow.show()
    viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->
      if (tvShows != null) {
        when (tvShows) {
          is Resource.Loading -> {
            binding.progressBarTvShow.show()
          }
          is Resource.Success -> {
            binding.progressBarTvShow.hide()
            adapter.submitList(tvShows.data)
            adapter.notifyDataSetChanged()
          }
          is Resource.Error -> {
            binding.progressBarTvShow.hide()
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