package com.example.stockproject.Activities;

import static app.utils.BasicUtils.generateString;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.android.volley.toolbox.Volley.newRequestQueue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import static app.utils.BasicUtils.generateString;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.stockproject.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import app.utils.BasicUtils;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CheckStockTest {
    private String firstUser = generateString(8);
    private String firstPassword = generateString(8);
    private String secondUser = generateString(8);
    private String secondPassword = generateString(8);

    @Rule
    public ActivityScenarioRule<LoginActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void checkStockTest() throws IOException, InterruptedException {
        BasicUtils.postMethod("http://coms-309-019.class.las.iastate.edu:8080/stock/post/TEMP/50");

        //Create first user
        onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.usernameInput)).perform(typeText(firstUser), closeSoftKeyboard());
        onView(withId(R.id.passwordInput)).perform(typeText(firstPassword), closeSoftKeyboard());
        onView(withId(R.id.userOrAdmin)).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        Thread.sleep(2000);

        //Find Stock
        onView(withId(R.id.stocks_page)).perform(click());
        onView(withId(R.id.stocksRecycler))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("TEMP")),
                        click()));
        onView(withId(R.id.numStocks)).perform(typeText("2"), closeSoftKeyboard());
        onView(withId(R.id.buyStocks)).perform(click());
        onView(withId(R.id.numStocks)).perform(pressKey(KeyEvent.KEYCODE_DEL));
        onView(withId(R.id.numStocks)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.sellStocks)).perform(click());
        onView(withId(R.id.home_button7)).perform(click());
        onView(withId(R.id.profile_page)).perform(click());
        onView(withId(R.id.holdingRecycler))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("TEMP")),
                        click()));
        onView(withId(R.id.home_button7));
        Thread.sleep(10000);
//        BasicUtils.postMethod("http://coms-309-019.class.las.iastate.edu:8080/person/delete" + firstUser);


//        BasicUtils.deleteMethod("http://coms-309-019.class.las.iastate.edu:8080/stock/delete/TEMP");


    }
}
