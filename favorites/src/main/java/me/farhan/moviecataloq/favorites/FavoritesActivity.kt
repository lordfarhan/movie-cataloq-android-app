package me.farhan.moviecataloq.favorites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.favorites.databinding.ActivityFavoritesBinding
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
    binding = ActivityFavoritesBinding.inflate(layoutInflater)
    setContentView(binding.root)

    loadKoinModules(favoritesModule)

    setSupportActionBar(binding.toolbarFavorites)
    supportActionBar?.title = resources.getString(R.string.favorites)

    adapter = FavoritesAdapter(this)
    binding.viewPager2Favorites.adapter = adapter
    binding.viewPager2Favorites.offscreenPageLimit = 2

    TabLayoutMediator(binding.tabLayoutFavorites, binding.viewPager2Favorites) { tab, position ->
      when (position) {
        0 -> tab.text = resources.getString(R.string.movies)
        1 -> tab.text = resources.getString(R.string.tv_shows)
      }
    }.attach()
  }
}