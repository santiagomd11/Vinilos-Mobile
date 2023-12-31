package com.example.vinilos;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestListAlbums {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testListAlbumLoadOK() throws InterruptedException {

        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Thread.sleep(500);
    }

    @Test
    public void testListAlbumOpenDetailOK() throws InterruptedException {

        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.click()));

        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.albumTitle))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.albumDescription))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.albumGenre))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.albumImage))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.albumReleaseDate))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.albumLabel))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
