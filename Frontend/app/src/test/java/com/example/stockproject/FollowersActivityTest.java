package com.example.stockproject;
import android.content.Intent;
import androidx.test.ext.rule.ActivityTestRule;
import androidx.test.ext.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class FollowersActivityTest {

    @Rule
    // third parameter is set to false which means the activity is not started automatically
    public ActivityTestRule<FollowersActivityTest> rule =
            new ActivityTestRule(FollowersActivityTest.class, true, false);

    @Test
    public void demonstrateIntentPrep() {
        Intent intent = new Intent();
        intent.putExtra("username", "Test");
        rule.launchActivity(intent);
        onView(withId(R.id.textView3)).check(matches(withText("Test")));
    }
}