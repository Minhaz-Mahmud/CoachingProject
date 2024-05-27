package com.example.coachingproject;

import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class HomePageUITest {

    @Rule
    public ActivityScenarioRule<HomePage> activityScenarioRule =
            new ActivityScenarioRule<>(HomePage.class);

//    @Test
//    public void testProfileCardClick() {
//        Intents.init();
//        Espresso.onView(ViewMatchers.withId(R.id.profile))
//                .perform(ViewActions.click());
//        intended(hasComponent(Account.class.getName()));
//        Intents.release();
//    }

    @Test
    public void testEmailCardClick() {
        Intents.init();
        Espresso.onView(ViewMatchers.withId(R.id.email))
                .perform(ViewActions.click());
        intended(hasAction(Intent.ACTION_VIEW));
        Intents.release();
    }

    @Test
    public void testPhoneCardClick() {
        Intents.init();
        Espresso.onView(ViewMatchers.withId(R.id.phone_us))
                .perform(ViewActions.click());
        intended(hasAction(Intent.ACTION_DIAL));
        Intents.release();
    }

    @Test
    public void testAboutUsCardClick() {
        Intents.init();
        Espresso.onView(ViewMatchers.withId(R.id.about_us))
                .perform(ViewActions.click());
        intended(hasComponent(AboutUs.class.getName()));
        Intents.release();
    }

    @Test
    public void testNavigationDrawerBuyCourses() {
        Intents.init();
        Espresso.onView(ViewMatchers.withContentDescription(R.string.app_name)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.buy_courses)).perform(ViewActions.click());
        intended(hasComponent(BuyCourses.class.getName()));
        Intents.release();
    }

    @Test
    public void testNavigationDrawerLogOut() {
        Intents.init();
        Espresso.onView(ViewMatchers.withContentDescription(R.string.app_name)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.log_out_drawer_item_id)).perform(ViewActions.click());
        intended(hasComponent(MainActivity.class.getName()));
        Intents.release();

        // Check if the user is logged out
        assertEquals(null, FirebaseAuth.getInstance().getCurrentUser());
    }

//    @Test
//    public void testNavigationDrawerYourCourses() {
//        Intents.init();
//        Espresso.onView(ViewMatchers.withContentDescription(R.string.app_name)).perform(ViewActions.click());
//        Espresso.onView(ViewMatchers.withId(R.id.your_courses)).perform(ViewActions.click());
//        intended(hasComponent(EnrolledCourses.class.getName()));
//        Intents.release();
//    }
}
