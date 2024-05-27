package com.example.coachingproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Calendar;

interface SplashTransitionStrategy {
    void transition(SplashScrean activity);
    void prepare(SplashScrean activity);
}

class DefaultSplashTransition implements SplashTransitionStrategy {
    @Override
    public void transition(SplashScrean activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public void prepare(SplashScrean activity) {
        activity.findViewById(R.id.Splash_image_view).setBackgroundColor(activity.getResources().getColor(android.R.color.holo_blue_light));
        TextView transitionIndicator = activity.findViewById(R.id.Splash_transition_indicator);
//        transitionIndicator.setText("Default Transition");
        transitionIndicator.setVisibility(android.view.View.VISIBLE);
    }
}

class FadeSplashTransition implements SplashTransitionStrategy {
    @Override
    public void transition(SplashScrean activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        activity.finish();
    }

    @Override
    public void prepare(SplashScrean activity) {
        activity.findViewById(R.id.Splash_image_view).setBackgroundColor(activity.getResources().getColor(android.R.color.holo_red_light));
        TextView transitionIndicator = activity.findViewById(R.id.Splash_transition_indicator);
//        transitionIndicator.setText("Fade Transition");
        transitionIndicator.setVisibility(android.view.View.VISIBLE);
    }
}

interface SplashTransitionFactory {
    SplashTransitionStrategy createTransitionStrategy();
}

class DefaultSplashTransitionFactory implements SplashTransitionFactory {
    @Override
    public SplashTransitionStrategy createTransitionStrategy() {
        return new DefaultSplashTransition();
    }
}

class FadeSplashTransitionFactory implements SplashTransitionFactory {
    @Override
    public SplashTransitionStrategy createTransitionStrategy() {
        return new FadeSplashTransition();
    }
}

public class SplashScrean extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 2500;
    SplashTransitionStrategy transitionStrategy;

    ImageView imageView;
    TextView textView1, textView2;
    Animation top, bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screan);

        imageView = findViewById(R.id.Splash_image_view);
        textView1 = findViewById(R.id.Splash_textView);
        textView2 = findViewById(R.id.Splash_textView2);

        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);
        imageView.setAnimation(top);
        textView1.setAnimation(bottom);
        textView2.setAnimation(bottom);

        SplashTransitionFactory factory = getSplashTransitionFactory();
        transitionStrategy = factory.createTransitionStrategy();
        transitionStrategy.prepare(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                transitionStrategy.transition(SplashScrean.this);
            }
        }, SPLASH_SCREEN);
    }

    SplashTransitionFactory getSplashTransitionFactory() {

        boolean useFadeTransition = getUserPreference();
        if (useFadeTransition) {
            return new FadeSplashTransitionFactory();
        } else {
            return new DefaultSplashTransitionFactory();
        }
    }

    boolean getUserPreference() {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);


        return hour >= 18 || hour < 6;
    }
}
