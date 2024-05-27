package com.example.coachingproject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AboutUsTest {

    @Rule
    public ActivityScenarioRule<AboutUs> activityScenarioRule =
            new ActivityScenarioRule<>(AboutUs.class);

    @Test
    public void testAboutUsTitleDisplayed() {
        onView(withId(R.id.title_text_view))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText("ABOUT US")));
    }

    @Test
    public void testAboutUsContentDisplayed() {
        onView(withId(R.id.about_us_text_view))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(withText(R.string.about_us)));
    }
}
