package me.farhan.moviecataloq.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.core.util.SPLASH_SCREEN_DURATION
import me.farhan.moviecataloq.databinding.ActivitySplashScreenBinding
import me.farhan.moviecataloq.ui.landing.LandingActivity

class SplashScreenActivity : AppCompatActivity() {

  private lateinit var binding: ActivitySplashScreenBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

    Handler().postDelayed({
      startActivity(Intent(this, LandingActivity::class.java))
      finish()
    }, SPLASH_SCREEN_DURATION)
  }
}