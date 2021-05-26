package me.farhan.moviecataloq.ui.landing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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

  private lateinit var binding: ActivityLandingBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityLandingBinding.inflate(layoutInflater)
    setContentView(binding.root)

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
        R.id.homeFragment,
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