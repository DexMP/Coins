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
import com.dexmp.dexmp.data.Consts;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Vars
    protected int coins;
    protected int xp;
    protected int lvl;
    protected int price_news;
    protected int sum_news;
    protected int price_finger;
    protected int sum_finger;
    protected int price_brain;
    protected int sum_brain;
    protected int price_mining_machine;
    protected int sum_mining_machine;
    protected int price_mining_farm;
    protected int sum_mining_farm;
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
    public Timer second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        loadProgress();
        updateUI();
        clicks();
        startTimer();
    }

     /*================================ Voids ================================*/

    @Override
    protected void onPause() {
        super.onPause();
        second.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        second.cancel();
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

    private void updateUI() {
        balance.setText(coins + Consts.GAME_VALUT);
        ui_lvl.setText("Уровень: " + lvl);
        progress_xp.setMax(Consts.X_XP * lvl * 5);
        progress_xp.setProgress(xp);
    }

    private void newLvl() {
        if (xp > Consts.X_XP * lvl * 5){
            // Edit vars
            lvl++;
            coins = coins + 1000;
            xp = 0;

            balance.setText(coins + Consts.GAME_VALUT);
            ui_lvl.setText("Уровень: " + lvl);
            progress_xp.setMax(Consts.X_XP * lvl * 5);
            progress_xp.setProgress(xp);

            saveProgress();
        }
    }

    private void animation() {
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
    }

    private void saveProgress() {
        SharedPreferences savedProgress = getSharedPreferences("Saver", MODE_PRIVATE);
        SharedPreferences.Editor editor = savedProgress.edit();
        editor.putInt("coins", coins);
        editor.putInt("lvl", lvl);
        editor.putInt("xp", xp);
        editor.putInt("price_news", price_news);
        editor.putInt("sum_news", sum_news);
        editor.putInt("sum_finger", sum_finger);
        editor.putInt("price_finger", price_finger);
        editor.putInt("sum_brain", sum_brain);
        editor.putInt("price_brain", price_brain);
        editor.putInt("sum_mining_machine", sum_mining_machine);
        editor.putInt("price_mining_machine", price_mining_machine);
        editor.putInt("price_mining_farm", price_mining_farm);
        editor.putInt("sum_mining_farm", sum_mining_farm);
        editor.putInt("damage", damage);
        editor.putInt("dps", dps);
        editor.apply();
    }

    private void loadProgress() {
        // Back Save
        SharedPreferences save_load = getSharedPreferences("Saver", MODE_PRIVATE);
        //default
        coins = save_load.getInt("coins", 0);
        xp = save_load.getInt("xp", 0);
        lvl = save_load.getInt("lvl", Consts.LVL);
        //news
        price_news = save_load.getInt("price_news", Consts.START_PRICE_NEWS);
        sum_news = save_load.getInt("sum_news", 0);
        //finger
        price_finger = save_load.getInt("price_finger", Consts.START_PRICE_FINGER);
        sum_finger = save_load.getInt("sum_finger", 0);
        //brain
        price_brain = save_load.getInt("price_brain", Consts.START_PRICE_BRAIN);
        sum_brain = save_load.getInt("sum_brain", 0);
        //mining machine
        price_mining_machine = save_load.getInt("price_mining_machine", Consts.START_PRICE_MINING_MACHINE);
        sum_mining_machine = save_load.getInt("sum_mining_machine", 0);
        //mining farm
        price_mining_farm = save_load.getInt("price_mining_farm", Consts.START_PRICE_MINING_FARM);
        sum_mining_farm = save_load.getInt("sum_mining_farm", 0);
        //full damage
        dps = save_load.getInt("dps", 0);
        damage = save_load.getInt("damage", Consts.START_DAMAGE);
    }

    private void clicks() {
        coin.setOnClickListener(this);
        shop.setOnClickListener(this);
        profile.setOnClickListener(this);
    }

    private void startTimer() {
        second = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                coins = coins + dps;
                updateUI();
                saveProgress();
            }
        };
        second.scheduleAtFixedRate(task, Consts.TIC, Consts.SECOND);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.coin:
                animation();
                //Edit vars
                coins = coins + damage;
                xp = xp + damage;
                balance.setText(coins + Consts.GAME_VALUT);
                ui_lvl.setText("Уровень: " + lvl);
                progress_xp.setMax(Consts.X_XP * lvl * 5);
                progress_xp.setProgress(xp);
                saveProgress();
                newLvl();
                break;
            case R.id.shop:
                startActivity(new Intent(MainActivity.this, Shop.class));
                onPause();
                break;
            case  R.id.profile:
                startActivity(new Intent(MainActivity.this, Profile.class));
                onPause();
                break;
        }
    }
}
