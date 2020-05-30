package com.dexmp.dexmp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dexmp.dexmp.data.Consts;

public class SplashScreen extends AppCompatActivity {

    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initData();

        YoYo.with(Techniques.FadeIn)
                .duration(Consts.SPLASH_SCREEN_ANIMATION)
                .repeat(0)
                .playOn(logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, Consts.SPLASH_TIME_OUT);
    }

    private void initData() {
        logo = findViewById(R.id.logo);
    }
}
