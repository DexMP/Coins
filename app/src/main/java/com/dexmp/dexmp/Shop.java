package com.dexmp.dexmp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.dexmp.dexmp.data.Consts;

public class Shop extends AppCompatActivity implements View.OnClickListener {

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
    //Layouts
    LinearLayout liner;
    // Text
    TextView shop_balance;
    TextView ui_news_price;
    TextView ui_news_sum;
    TextView ui_finger_price;
    TextView ui_finger_sum;
    TextView ui_brain_price;
    TextView ui_brain_sum;
    TextView ui_mining_machine_price;
    TextView ui_mining_machine_sum;
    TextView ui_mining_farm_price;
    TextView ui_mining_farm_sum;
    // Cards
    CardView news;
    CardView brain;
    CardView finger;
    CardView mining_machine;
    CardView mining_farm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        setTitle("Магазин");
        initData();
        loadProgress();
        checkMoney();
        updateUI();
    }

    /*================================ Voids ================================*/

    private void loadProgress() {
        // Back Save
        SharedPreferences save_load = getSharedPreferences("Saver", MODE_PRIVATE);
        //default
        coins = save_load.getInt("coins", 0);
        xp = save_load.getInt("xp", 0);
        lvl = save_load.getInt("lvl", 0);
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


    private void updateUI() {
        ui_news_price.setText(price_news + Consts.GAME_VALUT);
        ui_news_sum.setText(sum_news + " шт.");
        ui_finger_price.setText(price_finger + Consts.GAME_VALUT);
        ui_finger_sum.setText(sum_finger + " шт.");
        ui_brain_price.setText(price_brain + Consts.GAME_VALUT);
        ui_brain_sum.setText(sum_brain + " шт.");
        ui_mining_machine_price.setText(price_mining_machine + Consts.GAME_VALUT);
        ui_mining_machine_sum.setText(sum_mining_machine + " шт.");
        ui_mining_farm_price.setText(price_mining_farm + Consts.GAME_VALUT);
        ui_mining_farm_sum.setText(sum_mining_farm + " шт.");
        shop_balance.setText(coins + Consts.GAME_VALUT);

        checkMoney();
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

    private void checkMoney() {
        if (coins >= price_news) {
            news.setCardBackgroundColor(getResources().getColor(R.color.white));
            news.setOnClickListener(this);
        } else {
            news.setCardBackgroundColor(getResources().getColor(R.color.silver));
        }

        if (coins >= price_finger) {
            finger.setCardBackgroundColor(getResources().getColor(R.color.white));
            finger.setOnClickListener(this);
        } else {
            finger.setCardBackgroundColor(getResources().getColor(R.color.silver));
        }

        if (coins >= price_brain) {
            brain.setCardBackgroundColor(getResources().getColor(R.color.white));
            brain.setOnClickListener(this);
        } else {
            brain.setCardBackgroundColor(getResources().getColor(R.color.silver));
        }

        if (coins >= price_mining_machine) {
            mining_machine.setOnClickListener(this);
            mining_machine.setCardBackgroundColor(getResources().getColor(R.color.white));
        } else {
            mining_machine.setCardBackgroundColor(getResources().getColor(R.color.silver));
        }

        if (coins >= price_mining_farm) {
            mining_farm.setOnClickListener(this);
            mining_farm.setCardBackgroundColor(getResources().getColor(R.color.white));
        } else {
            mining_farm.setCardBackgroundColor(getResources().getColor(R.color.silver));
        }
    }

    private void initData() {
        liner = (LinearLayout) findViewById(R.id.shop_for_item);
        shop_balance = (TextView) findViewById(R.id.shop_balance);
        ui_news_price = (TextView) findViewById(R.id.news_price);
        ui_news_sum = (TextView) findViewById(R.id.news_sum);
        ui_finger_price = (TextView) findViewById(R.id.finger_price);
        ui_finger_sum = (TextView) findViewById(R.id.finger_sum);
        ui_brain_price = (TextView) findViewById(R.id.brain_price);
        ui_brain_sum = (TextView) findViewById(R.id.brain_sum);
        ui_mining_machine_price = (TextView) findViewById(R.id.mining_machine_price);
        ui_mining_machine_sum = (TextView) findViewById(R.id.mining_machine_sum);
        ui_mining_farm_price = (TextView) findViewById(R.id.mining_farm_price);
        ui_mining_farm_sum = (TextView) findViewById(R.id.mining_farm_sum);
        mining_machine = (CardView) findViewById(R.id.mining_machine);
        news = (CardView) findViewById(R.id.news);
        finger = (CardView) findViewById(R.id.finger);
        brain = (CardView) findViewById(R.id.brain);
        mining_farm = (CardView) findViewById(R.id.mining_farm);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.finger:
                if (coins >= price_finger) {
                    if (lvl >= 2) {
                        coins = coins - price_finger;
                        sum_finger++;
                        damage++;
                        price_finger = price_finger + 1000;
                        saveProgress();
                        updateUI();
                    } else {
                        Toast.makeText(this, "Уровень маловат не потянешь", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Эмм.. не хватает денег", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.brain:
                if (coins >= price_brain) {
                    if (lvl >= 10) {
                        coins = coins - price_brain;
                        sum_brain++;
                        damage = damage + 12;
                        price_brain = price_brain + 7000;

                        saveProgress();
                        updateUI();
                    } else {
                        Toast.makeText(this, "Уровень маловат не потянешь", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Эмм.. не хватает денег", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.mining_machine:
                if (coins >= price_mining_machine) {
                    if (lvl >= 17) {
                        coins = coins - price_mining_machine;
                        sum_mining_machine++;
                        damage = damage + 7;
                        dps = dps + 7;
                        price_mining_machine = price_mining_machine + 20000;
                        saveProgress();
                        updateUI();
                    } else {
                        Toast.makeText(this, "Уровень маловат не потянешь", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Эмм.. не хватает денег", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.mining_farm:
                if (coins >= price_mining_farm) {
                    if (lvl >= 22) {
                        coins = coins - price_mining_farm;
                        sum_mining_farm++;
                        damage = damage + 10;
                        dps = dps + 25;
                        price_mining_farm = price_mining_farm + 55000;
                        saveProgress();
                        updateUI();
                    } else {
                        Toast.makeText(this, "Уровень маловат не потянешь", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Эмм.. не хватает денег", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.news:
                if (coins >= price_news) {
                    if (lvl >= 1) {
                        coins = coins - price_news;
                        sum_news++;
                        dps = (dps + 1);
                        price_news = price_news + 150;
                        saveProgress();
                        updateUI();
                    } else {
                        Toast.makeText(this, "Уровень маловат не потянешь", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Эмм.. не хватает денег", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
