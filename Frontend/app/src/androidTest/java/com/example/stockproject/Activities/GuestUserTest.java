package com.example.stockproject.Activities;

import static androidx.core.util.Preconditions.checkNotNull;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static java.util.regex.Pattern.matches;

import android.util.EventLogTags;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.android.volley.toolbox.HttpResponse;
import com.example.stockproject.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;

import app.utils.BasicUtils;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GuestUserTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void guestUserTest() throws IOException {
//        BasicUtils.postMethod("http://coms-309-019.class.las.iastate.edu:8080/stock/post/WERP/50");



//        URL url = new URL("http://coms-309-019.class.las.iastate.edu:8080/stock/post/TEMP/50");
//        URLConnection con = url.openConnection();
//        HttpURLConnection http = (HttpURLConnection) con;
//        http.connect();

        onView(withId(R.id.LoginButton2)).perform(click());

        //Followers
        onView(withId(R.id.followers_page)).perform(click());
        onView(withId(R.id.toMain)).perform(click());

        //Profile
        onView(withId(R.id.profile_page)).perform(click());
        onView(withId(R.id.toLogin)).perform(click());
        onView(withId(R.id.LoginButton2)).perform(click());

        //Stocks
        onView(withId(R.id.stocks_page)).perform(click());
        onView(withId(R.id.stocksRecycler))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("TEMP")),
                        click()));
        onView(withId(R.id.numStocks)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.buyStocks)).perform(click());
        onView(withId(R.id.toMain)).perform(click());
        onView(withId(R.id.numStocks)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.sellStocks)).perform(click());
        onView(withId(R.id.toMain)).perform(click());
        onView(withId(R.id.home_button7)).perform(click());

        //


        //TODO: Update this request when we add a way to delete stocks...
        //BasicUtils.postMethod("http://coms-309-019.class.las.iastate.edu:8080/stock/post/QRST/50");


    }
}
