package com.jinll.mylife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class HelloActivity extends Activity implements Animation.AnimationListener{

    private TextView txtWelcome;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.m_hello);

        txtWelcome = (TextView) this.findViewById(R.id.txt_welcome);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Animation ani = new TranslateAnimation(600, 40, 700, 700);
        ani.setFillAfter(true);
        ani.setDuration(500);
        ani.setAnimationListener(this);

        txtWelcome.setVisibility(View.VISIBLE);
        txtWelcome.startAnimation(ani);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Intent indexIntent = new Intent(this, IndexActivity.class);
        startActivity(indexIntent);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
