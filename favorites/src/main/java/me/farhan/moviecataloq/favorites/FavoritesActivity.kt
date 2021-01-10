package me.farhan.moviecataloq.favorites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.databinding.ActivityFavoritesBinding
import org.koin.core.context.loadKoinModules

/**
 * @author farhan
 * created at at 14:28 on 28/11/20.
 */
class FavoritesActivity : AppCompatActivity() {

  private lateinit var adapter: FavoritesAdapter
  private lateinit var binding: ActivityFavoritesBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_favorites)

    loadKoinModules(favoritesModule)

    setSupportActionBar(binding.toolbarFavorite)
    supportActionBar?.title = resources.getString(R.string.favorites)

    adapter = FavoritesAdapter(this)
    binding.viewPager2Favorite.adapter = adapter
    binding.viewPager2Favorite.offscreenPageLimit = 2

    TabLayoutMediator(binding.tabLayoutFavorite, binding.viewPager2Favorite) { tab, position ->
      when (position) {
        0 -> tab.text = resources.getString(R.string.movies)
        1 -> tab.text = resources.getString(R.string.tv_shows)
      }
    }.attach()
  }
}