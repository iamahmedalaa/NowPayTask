package com.example.nowpaytask

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nowpaytask.presentation.ui.LoginActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class LoginActivityHiltTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityRule: ActivityScenarioRule<LoginActivity> =
        ActivityScenarioRule(LoginActivity::class.java)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testUserCanEnterEmailAndPassword() {

        onView(withId(R.id.emailEt))
            .perform(ViewActions.typeText("test@example.com"))
            .check(matches(ViewMatchers.withText("test@example.com")))

        onView(withId(R.id.passwordEt))
            .perform(ViewActions.typeText("password123"))
            .check(matches(ViewMatchers.withText("password123")))
            .perform(ViewActions.closeSoftKeyboard())
    }

    @Test
    fun testLoginButtonClick() {

        onView(withId(R.id.emailEt))
            .perform(ViewActions.typeText("test@example.com"))
        onView(withId(R.id.passwordEt))
            .perform(ViewActions.typeText("password123"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.loginBt))
            .perform(ViewActions.click())
    }

    @Test
    fun testEmailValidationErrorShown() {

        onView(withId(R.id.passwordEt))
            .perform(ViewActions.typeText("password123"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.loginBt))
            .perform(ViewActions.click())

        onView(withId(R.id.emailErrorTv))
            .check(
                matches(
                    Matchers.allOf(
                        isDisplayed()
                    )
                )
            )
    }

    @Test
    fun testPasswordValidationErrorShown() {

        onView(withId(R.id.emailEt))
            .perform(ViewActions.typeText("test@example.com"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.loginBt))
            .perform(ViewActions.click())

        onView(withId(R.id.passwordErrorTv))
            .check(
                matches(
                    Matchers.allOf(
                        isDisplayed()
                    )
                )
            )
    }

    @Test
    fun testNavigationToMovieListActivity() {

        onView(withId(R.id.emailEt))
            .perform(ViewActions.typeText("test@example.com"))
        onView(withId(R.id.passwordEt))
            .perform(ViewActions.typeText("password123"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.loginBt))
            .perform(ViewActions.click())

        onView(withId(R.id.rootView))
            .check(matches(isDisplayed()))
    }

}
