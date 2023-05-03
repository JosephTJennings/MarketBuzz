package com.example.stockproject;
import androidx.test.rule.ActivityTestRule;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.filters.LargeTest;

import com.example.stockproject.Activities.FollowersActivity;
import com.example.stockproject.Activities.MainActivity;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest   // large execution time
public class FollowersActivityTest {

//    @Rule
//    // third parameter is set to false which means the activity is not started automatically
//    public ActivityTestRule<FollowersActivity> rule =  new ActivityTestRule(FollowersActivity.class, true, false);

    @Rule   // needed to launch the activity
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Start the server and run this test
     */
    @Test
    public void reverseString(){
        String testString = "hello";
        String resultString = "olleh";
        // Type in testString and send request
        System.out.println("mlfoa");

    }
//    @Test
//    public void demonstrateIntentPrep() {
//        Intent intent = new Intent();
//        intent.putExtra("username", "Test");
//        rule.launchActivity(intent);
//        //onView(withId(R.id.textView3)).check(matches(withText("Test")));
//    }
}