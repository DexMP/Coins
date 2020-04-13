package com.dexmp.dexmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    // Vars
    protected int coins;
    protected int xp;
    protected int lvl;

    // UI
    public ImageView coin;
    public TextView balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        // Back Save
        SharedPreferences save_load = getSharedPreferences("Saver", MODE_PRIVATE);
        coins = save_load.getInt("coins", 0);
        balance.setText(save_load.getInt("coins", 0) + " P");
        xp = save_load.getInt("xp", 0);
        lvl = save_load.getInt("lvl", 0);

        // Output
        balance.setText(coins + " Р");

        if (xp > 1000 * lvl){
            // Edit vars
            lvl++;
            coins = coins * 2;
            xp = 0;
            balance.setText(coins + " Р");

            // Save progress
            SharedPreferences savedProgress = getSharedPreferences("Saver", MODE_PRIVATE);
            SharedPreferences.Editor editor = savedProgress.edit();
            editor.putInt("coins", coins);
            editor.putInt("xp", xp);
            editor.putInt("lvl", lvl);
            editor.apply();
        }

        coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Animation text
                YoYo.with(Techniques.Tada)
                        .duration(500)
                        .repeat(0)
                        .playOn(balance);

                // Animation button
                YoYo.with(Techniques.Pulse)
                        .duration(500)
                        .repeat(0)
                        .playOn(coin);

                //Edit vars
                coins++;
                xp++;
                balance.setText(coins + " Р");

                // Save progress
                SharedPreferences savedProgress = getSharedPreferences("Saver", MODE_PRIVATE);
                SharedPreferences.Editor editor = savedProgress.edit();
                editor.putInt("coins", coins);
                editor.putInt("xp", xp);
                editor.putInt("lvl", lvl);
                editor.apply();
            }
        });
    }

    private void initData() {
        coin = (ImageView) findViewById(R.id.coin);
        balance = (TextView) findViewById(R.id.balance);
    }
}
