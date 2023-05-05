package com.example.stockproject.Activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static app.utils.BasicUtils.generateString;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.stockproject.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CheckUserTest {
    private String firstUser = generateString(8);
    private String firstPassword = generateString(8);
    private String secondUser = generateString(8);
    private String secondPassword = generateString(8);

    @Rule
    public ActivityScenarioRule<LoginActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void checkUserTest() {
        //Create first user
        onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.usernameInput)).perform(typeText(firstUser), closeSoftKeyboard());
        onView(withId(R.id.passwordInput)).perform(typeText(firstPassword), closeSoftKeyboard());
        onView(withId(R.id.userOrAdmin)).perform(click());
        onView(withId(R.id.registerButton)).perform(click());

        onView(withId(R.id.logout_page)).perform(click());

        //Create second user
        onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.usernameInput)).perform(typeText(secondUser), closeSoftKeyboard());
        onView(withId(R.id.passwordInput)).perform(typeText(secondPassword), closeSoftKeyboard());
        onView(withId(R.id.userOrAdmin)).perform(click());
        onView(withId(R.id.registerButton)).perform(click());

//        onView(withId(R.id.leaderboard_page)).perform(click());
    }

}
