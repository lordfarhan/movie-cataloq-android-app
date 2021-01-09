package me.farhan.moviecataloq.core.util

import androidx.test.espresso.idling.CountingIdlingResource

/**
 * @author farhan
 * created at at 16:04 on 24/11/20.
 */
object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        espressoTestIdlingResource.decrement()
    }
}