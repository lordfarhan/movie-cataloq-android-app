package me.farhan.moviecataloq.ui.landing

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_landing.*
import me.farhan.moviecataloq.R

/**
 * @author farhan
 * created at at 8:59 on 23/10/2020.
 */
class LandingActivity : AppCompatActivity() {

    private lateinit var adapter: LandingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        setSupportActionBar(toolbar_landing)
        supportActionBar?.title = ""

        adapter = LandingAdapter(this)
        viewPager2_landing.adapter = adapter
        viewPager2_landing.offscreenPageLimit = 2

        TabLayoutMediator(tabLayout_landing, viewPager2_landing) { tab, position ->
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

}