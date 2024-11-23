package com.example.nowpaytask

import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nowpaytask.presentation.ui.MovieListActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.nowpaytask.presentation.MoviesViewModel
import com.example.nowpaytask.presentation.ui.MovieViewHolder
import kotlinx.coroutines.flow.onCompletion
import org.hamcrest.Matchers.allOf
import org.junit.Before

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MoviesListActivityTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityRule: ActivityScenarioRule<MovieListActivity> =
        ActivityScenarioRule(MovieListActivity::class.java)

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun testRecyclerViewDisplaysDataCorrectly() {
        activityRule.scenario.onActivity { activity ->
            val viewModel = ViewModelProvider(activity)[MoviesViewModel::class.java]

            viewModel.movies.onCompletion {
                onView(withId(R.id.recyclerViewMovies))
                    .check(matches(isDisplayed()))

                onView(withId(R.id.recyclerViewMovies))
                    .perform(RecyclerViewActions.scrollToPosition<MovieViewHolder>(0))
                    .check(
                        matches(
                            hasDescendant(
                                allOf(
                                    withId(R.id.tvTitle),
                                    withText(viewModel.movies.value.first().title)
                                )
                            )
                        )
                    )

                onView(withId(R.id.recyclerViewMovies))
                    .perform(RecyclerViewActions.scrollToPosition<MovieViewHolder>(1))
                    .check(
                        matches(
                            hasDescendant(
                                allOf(
                                    withId(R.id.tvTitle),
                                    withText(viewModel.movies.value[1].title)
                                )
                            )
                        )
                    )
            }
        }
    }


    @Test
    fun testRecyclerViewClickItem() {
        activityRule.scenario.onActivity { activity ->
            val viewModel = ViewModelProvider(activity)[MoviesViewModel::class.java]

            viewModel.movies.onCompletion {
                onView(withId(R.id.recyclerViewMovies))
                    .perform(
                        RecyclerViewActions.actionOnItemAtPosition<MovieViewHolder>(
                            0,
                            click()
                        )
                    )
                onView(withText("on Item Clicked"))
                    .inRoot(ToastMatcher())
                    .check(matches(isDisplayed()))
            }
        }


    }

    @Test
    fun testRecyclerViewScrollToEnd() {
        activityRule.scenario.onActivity { activity ->
            val viewModel = ViewModelProvider(activity)[MoviesViewModel::class.java]

            viewModel.movies.onCompletion {
                onView(withId(R.id.recyclerViewMovies))
                    .perform(
                        RecyclerViewActions.scrollToPosition<MovieViewHolder>(
                            viewModel.movies.value.size - 1
                        )
                    )
            }
        }
    }

    @Test
    fun testRecyclerViewDisplaysEmptyState() {
        activityRule.scenario.onActivity { activity ->
            val viewModel = ViewModelProvider(activity)[MoviesViewModel::class.java]
            viewModel.emptyState()
        }
        onView(withId(R.id.recyclerViewMovies))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
    }
}



