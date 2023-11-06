package com.example.vinilos;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestListArtists {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testListArtistLoadOK() throws InterruptedException {

        Espresso.onView(ViewMatchers.withId(R.id.artistsRV))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Thread.sleep(3000);
    }

    @Test
    public void testListArtistOpenDetailOK() throws InterruptedException {

        Espresso.onView(ViewMatchers.withId(R.id.artistsRV))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Thread.sleep(3000);

        Espresso.onView(ViewMatchers.withId(R.id.artistsRV))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.click()));

        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.artistTitle))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.artistDescription))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.artistImage))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}

