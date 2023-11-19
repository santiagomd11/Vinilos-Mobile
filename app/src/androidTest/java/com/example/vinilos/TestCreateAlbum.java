package com.example.vinilos;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestCreateAlbum {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testCreateAlbumButtonCancel() throws InterruptedException {
        Espresso.onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(withId(R.id.fab)).perform(click());

        Thread.sleep(200);

        Espresso.onView(withId(R.id.cancelSaveAlbumButton)).perform(click());

        Thread.sleep(500);

        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Thread.sleep(200);

    }

    @Test
    public void testCreateAlbumValidationFieldNameEmpty() throws InterruptedException {
        Espresso.onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(withId(R.id.fab)).perform(click());

        Thread.sleep(200);

        Espresso.onView(withId(R.id.saveAlbumButton)).perform(click());

        Thread.sleep(200);

        Espresso.onView(ViewMatchers.withText("El nombre es obligatorio"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testCreateAlbumValidationFieldCoverEmpty() throws InterruptedException {
        Espresso.onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(withId(R.id.fab)).perform(click());

        Thread.sleep(200);

        Espresso.onView(withId(R.id.saveAlbumButton)).perform(click());

        Thread.sleep(200);

        Espresso.onView(ViewMatchers.withText("La imagen de portada es obligatoria"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testCreateAlbumValidationFieldDescriptionEmpty() throws InterruptedException {
        Espresso.onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(withId(R.id.fab)).perform(click());

        Thread.sleep(200);

        Espresso.onView(withId(R.id.saveAlbumButton)).perform(click());

        Thread.sleep(200);

        Espresso.onView(ViewMatchers.withText("La descripción es obligatoria"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testCreateAlbumValidationFieldReleaseDateEmpty() throws InterruptedException {
        Espresso.onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(withId(R.id.fab)).perform(click());

        Thread.sleep(200);

        Espresso.onView(withId(R.id.saveAlbumButton)).perform(click());

        Thread.sleep(200);

        Espresso.onView(ViewMatchers.withText("La fecha de lanzamiento es obligatoria"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testCreateAlbumValidationFieldGenreEmpty() throws InterruptedException {
        Espresso.onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(withId(R.id.fab)).perform(click());

        Thread.sleep(200);

        Espresso.onView(withId(R.id.saveAlbumButton)).perform(click());

        Thread.sleep(200);

        Espresso.onView(ViewMatchers.withText("El género es obligatorio"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testCreateAlbumValidationFieldLabelEmpty() throws InterruptedException {
        Espresso.onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(withId(R.id.fab)).perform(click());

        Thread.sleep(200);

        Espresso.onView(withId(R.id.saveAlbumButton)).perform(click());

        Thread.sleep(200);

        Espresso.onView(ViewMatchers.withText("El sello discográfico es obligatorio"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
