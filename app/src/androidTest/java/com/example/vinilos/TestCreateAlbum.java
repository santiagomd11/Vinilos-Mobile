package com.example.vinilos;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.matcher.RootMatchers;
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

        Espresso.onView(ViewMatchers.withText("Name is required"))
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

        Espresso.onView(ViewMatchers.withText("Cover is required"))
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

        Espresso.onView(ViewMatchers.withText("Description is required"))
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

        Espresso.onView(ViewMatchers.withText("Release date is required"))
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

        Espresso.onView(ViewMatchers.withText("Genre is required"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testCreateAlbumValidationFieldRecordLabelEmpty() throws InterruptedException {
        Espresso.onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(withId(R.id.fab)).perform(click());

        Thread.sleep(200);

        Espresso.onView(withId(R.id.saveAlbumButton)).perform(click());

        Thread.sleep(200);

        Espresso.onView(ViewMatchers.withText("Record label is required"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testCreateAlbumValidationGenreNotAllowed() throws InterruptedException {
        Espresso.onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(withId(R.id.fab)).perform(click());

        Thread.sleep(200);

        Espresso.onView(withId(R.id.textNameField))
                .perform(replaceText("Album Genre"), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.textCoverField))
                .perform(replaceText("Portada Genre"), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.textReleaseDateField))
                .perform(replaceText("11/17/23"), closeSoftKeyboard());

        Espresso.onView(withId(R.id.textDescriptionField))
                .perform(replaceText("Esta es la descripcion del album para genre"), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.textGenreField))
                .perform(replaceText("Cualquiera"), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.textLabelField))
                .perform(replaceText("Cualquiera"), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.saveAlbumButton)).perform(click());

        Thread.sleep(200);

        Espresso.onView(ViewMatchers.withText("Error creating album"))
                .inRoot(RootMatchers.isDialog())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Thread.sleep(200);
    }

    @Test
    public void testCreateAlbumValidationRecordLabelNotAllowed() throws InterruptedException {
        Espresso.onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(withId(R.id.fab)).perform(click());

        Thread.sleep(200);

        Espresso.onView(withId(R.id.textNameField))
                .perform(replaceText("Album record label"), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.textCoverField))
                .perform(replaceText("Portada record label"), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.textReleaseDateField))
                .perform(replaceText("11/17/23"), closeSoftKeyboard());

        Espresso.onView(withId(R.id.textDescriptionField))
                .perform(replaceText("Esta es la descripcion del album record label"), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.textGenreField))
                .perform(replaceText("Rock"), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.textLabelField))
                .perform(replaceText("Cualquiera"), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.saveAlbumButton)).perform(click());

        Thread.sleep(200);

        Espresso.onView(ViewMatchers.withText("Error creating album"))
                .inRoot(RootMatchers.isDialog())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Thread.sleep(200);
    }

    @Test
    public void testCreateAlbumSuccess() throws InterruptedException {
        Espresso.onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(500);

        Espresso.onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_albums));
        Thread.sleep(500);

        Espresso.onView(withId(R.id.fab)).perform(click());

        Thread.sleep(200);

        Random random = new Random();
        int randomNumber = random.nextInt(100000000);

        Espresso.onView(withId(R.id.textNameField))
                .perform(replaceText("Album " + randomNumber), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.textCoverField))
                .perform(replaceText("https://images.squarespace-cdn.com/content/v1/5d2e2c5ef24531000113c2a4/1564770289250-9FPM7TAI5O56U9JQTPVO/album-placeholder.png?format=500w"), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.textReleaseDateField))
                .perform(replaceText("11/17/23"), closeSoftKeyboard());

        Espresso.onView(withId(R.id.textDescriptionField))
                .perform(replaceText("Esta es la descripcion del album " + randomNumber), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.textGenreField))
                .perform(replaceText("Rock"), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.textLabelField))
                .perform(replaceText("EMI"), closeSoftKeyboard());
        Thread.sleep(100);

        Espresso.onView(withId(R.id.saveAlbumButton)).perform(click());

        Espresso.onView(ViewMatchers.withText("Album created successfully"))
                .inRoot(RootMatchers.isDialog())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Thread.sleep(200);
    }
}
