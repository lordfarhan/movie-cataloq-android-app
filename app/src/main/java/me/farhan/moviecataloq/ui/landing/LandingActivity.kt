package me.farhan.moviecataloq.ui.landing

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.databinding.ActivityLandingBinding
import me.farhan.moviecataloq.ui.favorite.FavoriteActivity

/**
 * @author farhan
 * created at at 8:59 on 23/10/2020.
 */
class LandingActivity : AppCompatActivity() {

  private lateinit var adapter: LandingAdapter
  private lateinit var binding: ActivityLandingBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_landing)

    setSupportActionBar(binding.toolbarLanding)
    supportActionBar?.title = ""

    adapter = LandingAdapter(this)
    binding.viewPager2Landing.adapter = adapter
    binding.viewPager2Landing.offscreenPageLimit = 2

    TabLayoutMediator(binding.tabLayoutLanding, binding.viewPager2Landing) { tab, position ->
      when (position) {
        0 -> tab.text = resources.getString(R.string.movies)
        1 -> tab.text = resources.getString(R.string.tv_shows)
      }
    }.attach()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_landing, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.menu_favorite -> {
        startActivity(Intent(this, FavoriteActivity::class.java))
        return true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

}