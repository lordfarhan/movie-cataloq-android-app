package me.farhan.moviecataloq.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.core.util.SPLASH_SCREEN_DURATION
import me.farhan.moviecataloq.databinding.ActivitySplashScreenBinding
import me.farhan.moviecataloq.ui.landing.LandingActivity

class SplashScreenActivity : AppCompatActivity() {

  private lateinit var binding: ActivitySplashScreenBinding
  private val activityScope = CoroutineScope(Dispatchers.Main)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

    activityScope.launch {
      delay(SPLASH_SCREEN_DURATION)

      startActivity(Intent(this@SplashScreenActivity, LandingActivity::class.java))
      finish()
    }
  }
}