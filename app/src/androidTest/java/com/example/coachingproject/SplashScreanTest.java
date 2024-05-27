package com.example.coachingproject;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility;

@RunWith(AndroidJUnit4.class)
public class SplashScreanTest {

    @Rule
    public ActivityScenarioRule<SplashScrean> activityScenarioRule = new ActivityScenarioRule<>(SplashScrean.class);

    @Test
    public void testSplashScreenDisplaysCorrectly() {
        // Check that the splash screen image and text views are displayed
        onView(withId(R.id.Splash_image_view)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(withId(R.id.Splash_textView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(withId(R.id.Splash_textView2)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
    }

}
