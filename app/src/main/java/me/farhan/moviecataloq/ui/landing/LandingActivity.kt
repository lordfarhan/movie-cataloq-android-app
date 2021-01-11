package me.farhan.moviecataloq.ui.landing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.databinding.ActivityLandingBinding
import me.farhan.moviecataloq.ui.settings.SettingsActivity

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

//    setSupportActionBar(binding.toolbarLanding)
//    supportActionBar?.title = ""

//    adapter = LandingAdapter(this)
//    binding.viewPager2Landing.adapter = adapter
//    binding.viewPager2Landing.offscreenPageLimit = 2
//
//    TabLayoutMediator(binding.tabLayoutLanding, binding.viewPager2Landing) { tab, position ->
//      when (position) {
//        0 -> tab.text = resources.getString(R.string.movies)
//        1 -> tab.text = resources.getString(R.string.tv_shows)
//      }
//    }.attach()

    setupViews()
  }

  private fun setupViews() {
    val navHostFragment = supportFragmentManager
      .findFragmentById(R.id.fragmentContainerView_landing) as NavHostFragment
    val navController = navHostFragment.navController
    binding.bottomNavigationViewLanding.setupWithNavController(navController)

    setSupportActionBar(binding.toolbarLanding)

    val appBarConfiguration = AppBarConfiguration(
      topLevelDestinationIds = setOf(
        R.id.movieFragment,
        R.id.tvShowFragment
      )
    )
    setupActionBarWithNavController(navController, appBarConfiguration)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_landing, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.menu_favorites -> {
        val uri = Uri.parse("moviecataloq://favorites")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
        return true
      }
      R.id.menu_settings -> {
        startActivity(Intent(this, SettingsActivity::class.java))
        return true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

}