package me.farhan.moviecataloq.ui.landing

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.util.DataDummy
import org.junit.Rule
import org.junit.Test

/**
 * @author farhan
 * created at at 19:00 on 02/11/2020.
 */
class LandingActivityTest {
    private val dummyMovies = DataDummy.getMovies()
    private val dummyTvShows = DataDummy.getTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(LandingActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.recyclerView_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.recyclerView_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.imageView_coverDetail)).check(matches(isDisplayed()))

        onView(withId(R.id.textView_titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.textView_titleDetail)).check(matches(withText(dummyMovies[0].title)))

        onView(withId(R.id.textView_ratingDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.textView_ratingDetail)).check(
            matches(
                withText(
                    String.format(
                        "%d%% (%d)",
                        dummyMovies[0].rating.toInt(),
                        dummyMovies[0].ratingAmount
                    )
                )
            )
        )

        onView(withId(R.id.textView_directorDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.textView_directorDetail)).check(matches(withText(dummyMovies[0].director)))

        onView(withId(R.id.textView_genreDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.textView_genreDetail)).check(matches(withText(dummyMovies[0].genres)))

        onView(withId(R.id.textView_overviewDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.textView_overviewDetail)).check(matches(withText(dummyMovies[0].overview)))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.recyclerView_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_tvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadTvShowDetail() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.recyclerView_tvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.imageView_coverDetail)).check(matches(isDisplayed()))

        onView(withId(R.id.textView_titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.textView_titleDetail)).check(matches(withText(dummyTvShows[0].title)))

        onView(withId(R.id.textView_ratingDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.textView_ratingDetail)).check(
            matches(
                withText(
                    String.format(
                        "%d%% (%d)",
                        dummyTvShows[0].rating.toInt(),
                        dummyTvShows[0].ratingAmount
                    )
                )
            )
        )

        onView(withId(R.id.textView_directorDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.textView_directorDetail)).check(matches(withText(dummyTvShows[0].director)))

        onView(withId(R.id.textView_genreDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.textView_genreDetail)).check(matches(withText(dummyTvShows[0].genres)))

        onView(withId(R.id.textView_overviewDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.textView_overviewDetail)).check(matches(withText(dummyTvShows[0].overview)))
    }
}