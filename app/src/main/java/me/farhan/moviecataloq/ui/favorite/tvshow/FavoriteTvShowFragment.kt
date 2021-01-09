package me.farhan.moviecataloq.ui.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.farhan.moviecataloq.core.domain.model.TvShow
import me.farhan.moviecataloq.core.ui.favorite.tvshow.FavoriteTvShowAdapter
import me.farhan.moviecataloq.core.util.hide
import me.farhan.moviecataloq.core.util.show
import me.farhan.moviecataloq.databinding.FragmentFavoriteTvshowBinding
import me.farhan.moviecataloq.interfaces.TvShowClickListener
import me.farhan.moviecataloq.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * @author farhan
 * created at at 14:35 on 28/11/20.
 */
class FavoriteTvShowFragment : Fragment(), TvShowClickListener {

  private val viewModel: FavoriteTvShowViewModel by viewModel()
  private lateinit var adapter: FavoriteTvShowAdapter
  private lateinit var binding: FragmentFavoriteTvshowBinding

  companion object {
    fun newInstance(): Fragment {
      val args = Bundle()

      val fragment = FavoriteTvShowFragment()
      fragment.arguments = args
      return fragment
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentFavoriteTvshowBinding.inflate(inflater, container, false)
    context ?: return binding.root
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if (activity != null) {
      adapter = FavoriteTvShowAdapter()
      adapter.listener = this
      binding.recyclerViewFavoriteTvShow.adapter = adapter

      subscribeInterface()
    }
  }

  private fun subscribeInterface() {
    binding.progressBarFavoriteTvShow.show()
    viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { favoriteTvShows ->
      if (favoriteTvShows != null) {
        binding.progressBarFavoriteTvShow.hide()
        adapter.submitList(favoriteTvShows)
        adapter.notifyDataSetChanged()
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