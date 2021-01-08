package me.farhan.moviecataloq.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.ui.landing.LandingActivity
import me.farhan.moviecataloq.core.util.SPLASH_SCREEN_DURATION

class SplashScreenActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash_screen)

    Handler().postDelayed({
      startActivity(Intent(this, LandingActivity::class.java))
      finish()
    }, SPLASH_SCREEN_DURATION)
  }
}