package com.dexmp.dexmp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    // Vars
    protected int coins;
    protected int xp;
    protected int lvl;
    protected int price_finger;
    protected int sum_finger;
    protected int price_brain;
    protected int sum_brain;
    protected int price_mining_machine;
    protected int sum_mining_machine;
    protected int dps;
    protected int damage;

    // UI
    // Images
    public ImageView shop;
    public ImageView coin;
    public ImageView profile;
    // Text
    public TextView balance;
    public TextView ui_xp;
    public TextView ui_lvl;
    // Components
    public FrameLayout back_root;
    public ProgressBar progress_xp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        // Back Save
        SharedPreferences save_load = getSharedPreferences("Saver", MODE_PRIVATE);
        //default
        coins = save_load.getInt("coins", 0);
        balance.setText(save_load.getInt("coins", 0) + Consts.GAME_VALUT);
        xp = save_load.getInt("xp", 0);
        lvl = save_load.getInt("lvl", 0);
        //finger
        price_finger = save_load.getInt("price_finger", Consts.START_PRICE_RAM);
        sum_finger = save_load.getInt("sum_finger", 0);
        //brain
        price_brain = save_load.getInt("price_brain", Consts.START_PRICE_BRAIN);
        sum_brain = save_load.getInt("sum_brain", 0);
        //mining machine
        price_mining_machine = save_load.getInt("price_mining_machine", Consts.START_PRICE_MINING_MACHINE);
        sum_mining_machine = save_load.getInt("sum_mining_machine", 0);
        //full damage
        dps = save_load.getInt("dps", 0);
        damage = save_load.getInt("damage", Consts.START_DAMAGE);

        // Output
        balance.setText(coins + Consts.GAME_VALUT);
        ui_lvl.setText("Уровень: " + lvl);
        progress_xp.setMax(Consts.X_XP * lvl);
        progress_xp.setProgress(xp);

        coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Animation balance
                YoYo.with(Techniques.Tada)
                        .duration(Consts.DEFAULT_ANIMATION)
                        .repeat(0)
                        .playOn(balance);

                // Animation button
                YoYo.with(Techniques.Pulse)
                        .duration(Consts.DEFAULT_ANIMATION)
                        .repeat(0)
                        .playOn(coin);

                //Edit vars
                coins = coins + damage;
                xp = xp + damage;
                balance.setText(coins + Consts.GAME_VALUT);
                ui_lvl.setText("Уровень: " + lvl);
                progress_xp.setMax(Consts.X_XP * lvl);
                progress_xp.setProgress(xp);

                // Save progress
                SharedPreferences savedProgress = getSharedPreferences("Saver", MODE_PRIVATE);
                SharedPreferences.Editor editor = savedProgress.edit();
                editor.putInt("coins", coins);
                editor.putInt("xp", xp);
                editor.putInt("lvl", lvl);
                editor.apply();

                if (xp > Consts.X_XP * lvl){
                    // Edit vars
                    lvl++;
                    coins = coins + 1000;
                    xp = 0;

                    balance.setText(coins + Consts.GAME_VALUT);
                    ui_lvl.setText("Уровень: " + lvl);
                    progress_xp.setMax(Consts.X_XP * lvl);
                    progress_xp.setProgress(xp);

                    // Save progress
                    SharedPreferences sProgress = getSharedPreferences("Saver", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sProgress.edit();
                    editor1.putInt("coins", coins);
                    editor1.putInt("xp", xp);
                    editor1.putInt("lvl", lvl);
                    editor1.apply();
                }
            }
        });

        // Shop
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Shop.class));
            }
        });

        // Profile
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        });
    }

     /*================================ Important Section! ================================*/

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initData() {
        coin = (ImageView) findViewById(R.id.coin);
        balance = (TextView) findViewById(R.id.balance);
        ui_xp = (TextView) findViewById(R.id.ui_xp);
        ui_lvl = (TextView) findViewById(R.id.ui_lvl);
        back_root = (FrameLayout) findViewById(R.id.back_root);
        shop = (ImageView) findViewById(R.id.shop);
        progress_xp = (ProgressBar) findViewById(R.id.progress_xp);
        profile = (ImageView) findViewById(R.id.profile);
    }
}
