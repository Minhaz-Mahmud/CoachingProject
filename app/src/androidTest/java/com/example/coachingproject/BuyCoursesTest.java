package com.example.coachingproject;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BuyCoursesTest {

    @Before
    public void setUp() {
        Intents.init();
        ActivityScenario.launch(BuyCourses.class);
    }

    @After
    public void tearDown() {
        Intents.release();
    }


    @Test
    public void testSscAcademicButton() {
        onView(withId(R.id.ssc_academic)).perform(click());
        Intents.intended(allOf(
                hasComponent(Transaction.class.getName()),
                hasExtra("course", "SSC ACADEMIC FULL COURSE")
        ));
    }

    @Test
    public void testSscModelTestButton() {
        onView(withId(R.id.ssc_model_test)).perform(click());
        Intents.intended(allOf(
                hasComponent(Transaction.class.getName()),
                hasExtra("course", "SSC MODEL TEST")
        ));
    }

    
}
