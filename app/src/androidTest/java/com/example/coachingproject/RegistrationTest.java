package com.example.coachingproject;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;

@RunWith(AndroidJUnit4.class)
public class RegistrationTest {

    @Rule
    public ActivityScenarioRule<Registration> activityRule = new ActivityScenarioRule<>(Registration.class);

//    @Test
//    public void testEmptyEmailShowsError() {
//        // Attempt to click the register button without entering an email
//        onView(withId(R.id.registration)).perform(click());
//
//        // Check that the email field shows the error message
//        onView(withId(R.id.regEmail)).check(matches(hasErrorText("Email is Required.")));
//    }

    @Test
    public void testEmptyEmailShowsError() {
        // Attempt to click the register button without entering an email
        onView(withId(R.id.registration)).perform(click());

        // Add a small delay to ensure the UI updates the error message
        try {
            Thread.sleep(500); // This is a simple delay for debugging purposes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check that the email field shows the error message
        onView(withId(R.id.regEmail)).check(matches(hasErrorText("Email is Required.")));
    }



    @Test
    public void testShortPasswordShowsError() {
        // Enter a valid email and a short password
        onView(withId(R.id.regEmail))
                .perform(typeText("test@example.com"), closeSoftKeyboard());
        onView(withId(R.id.regPassword))
                .perform(typeText("pass"), closeSoftKeyboard());

        // Attempt to click the register button
        onView(withId(R.id.registration)).perform(click());

        // Check that the password field shows the error message
        onView(withId(R.id.regPassword))
                .check(matches(hasErrorText("Password must be greater or equal to 6 letters")));
    }

//    @Test
//    public void testEmptyPasswordShowsError() {
//        // Enter a valid email but leave the password field empty
//        onView(withId(R.id.regEmail))
//                .perform(typeText("test@example.com"), closeSoftKeyboard());
//
//        // Attempt to click the register button
//        onView(withId(R.id.registration)).perform(click());
//
//        // Check that the password field shows the error message
//        onView(withId(R.id.regPassword))
//                .check(matches(hasErrorText("Password is Required.")));
//    }
}
