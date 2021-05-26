package me.farhan.moviecataloq.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.databinding.ActivitySettingsBinding

/**
 * @author farhan
 * created at at 21:41 on 10/01/21.
 */
class SettingsActivity : AppCompatActivity() {

  private lateinit var binding: ActivitySettingsBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivitySettingsBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setSupportActionBar(binding.toolbarSettings)
    supportActionBar?.title = resources.getString(R.string.settings)

    supportFragmentManager
      .beginTransaction()
      .replace(R.id.frameLayout_settingsContent, SettingsFragment())
      .commit()
  }
}