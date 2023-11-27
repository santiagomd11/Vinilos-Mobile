package com.example.vinilos;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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

import java.util.Random;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestAddTrackToAlbum {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void testAddTrackSuccess() throws InterruptedException {

        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.click()));

        Thread.sleep(500);

        Espresso.onView(withId(R.id.fab1)).perform(click());

        Random random = new Random();
        int randomNumber = random.nextInt(100000000);

        Espresso.onView(withId(R.id.textNameField))
                .perform(replaceText("Track " + randomNumber), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.textLengthField))
                .perform(replaceText("7:00"), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.saveTrackButton)).perform(click());

        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withText("Canción agregada exitosamente"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testAddTrackFieldNameEmpty() throws InterruptedException {

        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.click()));

        Thread.sleep(500);

        Espresso.onView(withId(R.id.fab1)).perform(click());

        Espresso.onView(withId(R.id.saveTrackButton)).perform(click());

        Espresso.onView(ViewMatchers.withText("El nombre es obligatorio"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testAddTrackFieldDurationEmpty() throws InterruptedException {

        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.click()));

        Thread.sleep(500);

        Espresso.onView(withId(R.id.fab1)).perform(click());

        Espresso.onView(withId(R.id.saveTrackButton)).perform(click());

        Espresso.onView(ViewMatchers.withText("La duración es obligatoria"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
