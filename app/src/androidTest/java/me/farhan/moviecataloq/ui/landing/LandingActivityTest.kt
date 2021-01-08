package me.farhan.moviecataloq.ui.landing

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import me.farhan.moviecataloq.R
import me.farhan.moviecataloq.core.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author farhan
 * created at at 19:00 on 02/11/2020.
 */
class LandingActivityTest {

  @get:Rule
  var activityRule = ActivityScenarioRule(LandingActivity::class.java)

  @Before
  fun setUp() {
    IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
  }

  @After
  fun tearDown() {
    IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
  }

  @Test
  fun loadMovies() {
    onView(withId(R.id.recyclerView_movie)).check(matches(isDisplayed()))
  }

  @Test
  fun loadMovieDetail() {
    onView(withId(R.id.recyclerView_movie)).perform(
      RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
        0,
        click()
      )
    )

    onView(withId(R.id.imageView_backCoverDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.imageView_coverDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_titleDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_taglineDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_ratingDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_dateDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_statusDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_overviewDetail)).check(matches(isDisplayed()))
  }

  @Test
  fun loadTvShows() {
    onView(withText("TV Shows")).perform(click())

    onView(withId(R.id.recyclerView_tvShow)).check(matches(isDisplayed()))
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

    onView(withId(R.id.imageView_backCoverDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.imageView_coverDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_titleDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_taglineDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_ratingDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_dateDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_statusDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_overviewDetail)).check(matches(isDisplayed()))
  }

  @Test
  fun loadFavoriteMoviesAndDetail() {
    onView(withId(R.id.recyclerView_movie)).perform(
      RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
        0,
        click()
      )
    )

    onView(withId(R.id.floatingActionButton_favorite)).perform(click())

    onView(isRoot()).perform(ViewActions.pressBack())

    onView(withId(R.id.menu_favorite)).perform(click())

    onView(withId(R.id.recyclerView_favoriteMovie)).check(matches(isDisplayed()))

    onView(withId(R.id.recyclerView_favoriteMovie)).perform(
      RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
        0,
        click()
      )
    )

    onView(withId(R.id.imageView_backCoverDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.imageView_coverDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_titleDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_taglineDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_ratingDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_dateDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_statusDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_overviewDetail)).check(matches(isDisplayed()))
  }

  @Test
  fun loadFavoriteTvShowsListAndDetail() {
    onView(withText("TV Shows")).perform(click())

    onView(withId(R.id.recyclerView_tvShow)).perform(
      RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
        0,
        click()
      )
    )

    onView(withId(R.id.floatingActionButton_favorite)).perform(click())

    onView(isRoot()).perform(ViewActions.pressBack())

    onView(withId(R.id.menu_favorite)).perform(click())

    onView(withText("TV Shows")).perform(click())

    onView(withId(R.id.recyclerView_favoriteTvShow)).check(matches(isDisplayed()))

    onView(withId(R.id.recyclerView_favoriteTvShow)).perform(
      RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
        0,
        click()
      )
    )

    onView(withId(R.id.imageView_backCoverDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.imageView_coverDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_titleDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_taglineDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_ratingDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_dateDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_statusDetail)).check(matches(isDisplayed()))

    onView(withId(R.id.textView_overviewDetail)).check(matches(isDisplayed()))
  }
}