package com.example.stockproject.Activities;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
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

import static java.util.regex.Pattern.matches;
import static app.utils.BasicUtils.generateString;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
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
public class AddFollowerTest {

    private String firstUser = generateString(8);
    private String firstPassword = generateString(8);
    private String secondUser = generateString(8);
    private String secondPassword = generateString(8);

    @Rule
    public ActivityScenarioRule<LoginActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void addFollowerTest() throws IOException {

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

        //Follow the second user with the first user
        onView(withId(R.id.followers_page)).perform(click());
        onView(withId(R.id.searchBar)).perform(typeText(firstUser), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.refresh_button)).perform(click());
        onView(withId(R.id.home_button)).perform(click());
        onView(withId(R.id.followers_page)).perform(click());
        BasicUtils.deleteMethod("http://coms-309-019.class.las.iastate.edu:8080/person/delete" + firstUser);
        BasicUtils.deleteMethod("http://coms-309-019.class.las.iastate.edu:8080/person/delete" + secondUser);
//        onView(ViewMatchers.withId(R.id.recycle_followers))
//                .perform(RecyclerViewActions.scrollTo(
//                hasDescendant(withText(firstUser))));
















//        ViewInteraction materialButton = onView(
//                allOf(withId(R.id.RegisterButton), withText("Register Here"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        materialButton.perform(click());
//
//        ViewInteraction appCompatEditText = onView(
//                allOf(withId(R.id.usernameInput),
//                        childAtPosition(
//                                allOf(withId(R.id.Register),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        appCompatEditText.perform(replaceText(firstUser), closeSoftKeyboard());
//
//        ViewInteraction appCompatEditText2 = onView(
//                allOf(withId(R.id.passwordInput),
//                        childAtPosition(
//                                allOf(withId(R.id.Register),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                0),
//                        isDisplayed()));
//        appCompatEditText2.perform(replaceText(firstPassword), closeSoftKeyboard());
//
//        ViewInteraction switch_ = onView(
//                allOf(withId(R.id.userOrAdmin), withText("Admin?"),
//                        childAtPosition(
//                                allOf(withId(R.id.Register),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                4),
//                        isDisplayed()));
//        switch_.perform(click());
//
//        ViewInteraction materialButton2 = onView(
//                allOf(withId(R.id.registerButton), withText("Register"),
//                        childAtPosition(
//                                allOf(withId(R.id.Register),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                2),
//                        isDisplayed()));
//        materialButton2.perform(click());
//
//        ViewInteraction materialButton3 = onView(
//                allOf(withId(R.id.logout_page), withText("Logout"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                4),
//                        isDisplayed()));
//        materialButton3.perform(click());
//
//        ViewInteraction materialButton4 = onView(
//                allOf(withId(R.id.RegisterButton), withText("Register Here"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        materialButton4.perform(click());
//
//        ViewInteraction appCompatEditText3 = onView(
//                allOf(withId(R.id.usernameInput),
//                        childAtPosition(
//                                allOf(withId(R.id.Register),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        appCompatEditText3.perform(replaceText(secondUser), closeSoftKeyboard());
//
//        ViewInteraction appCompatEditText4 = onView(
//                allOf(withId(R.id.passwordInput),
//                        childAtPosition(
//                                allOf(withId(R.id.Register),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                0),
//                        isDisplayed()));
//        appCompatEditText4.perform(replaceText(secondPassword), closeSoftKeyboard());
//
//        ViewInteraction switch_2 = onView(
//                allOf(withId(R.id.userOrAdmin), withText("Admin?"),
//                        childAtPosition(
//                                allOf(withId(R.id.Register),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                4),
//                        isDisplayed()));
//        switch_2.perform(click());
//
//        ViewInteraction materialButton5 = onView(
//                allOf(withId(R.id.registerButton), withText("Register"),
//                        childAtPosition(
//                                allOf(withId(R.id.Register),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                2),
//                        isDisplayed()));
//        materialButton5.perform(click());
//
//        ViewInteraction materialButton6 = onView(
//                allOf(withId(R.id.followers_page), withText("following"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                0),
//                        isDisplayed()));
//        materialButton6.perform(click());
//
//        ViewInteraction searchAutoComplete = onView(
//                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
//                        childAtPosition(
//                                allOf(withClassName(is("android.widget.LinearLayout")),
//                                        childAtPosition(
//                                                withClassName(is("android.widget.LinearLayout")),
//                                                1)),
//                                0),
//                        isDisplayed()));
//        searchAutoComplete.perform(replaceText(firstUser));
//        pressKey(KeyEvent.KEYCODE_ENTER);
//
//        ViewInteraction materialButton12 = onView(
//                allOf(withId(R.id.refresh_button), withText("refresh"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                4),
//                        isDisplayed()));
//        materialButton12.perform(click());
//
//        ViewInteraction materialButton7 = onView(
//                allOf(withId(R.id.home_button), withText("Home"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        materialButton7.perform(click());
//
//        ViewInteraction materialButton8 = onView(
//                allOf(withId(R.id.followers_page), withText("following"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                0),
//                        isDisplayed()));
//        materialButton8.perform(click());
//
//        ViewInteraction materialButton9 = onView(
//                allOf(withId(R.id.refresh_button), withText("refresh"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                4),
//                        isDisplayed()));
//        materialButton9.perform(click());
//
//        ViewInteraction materialButton10 = onView(
//                allOf(withId(R.id.home_button), withText("Home"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        materialButton10.perform(click());
//
//        ViewInteraction materialButton11 = onView(
//                allOf(withId(R.id.logout_page), withText("Logout"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                4),
//                        isDisplayed()));
//        materialButton11.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
