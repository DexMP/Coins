package com.dexmp.dexmp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Shop extends AppCompatActivity implements View.OnClickListener {

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
    //Layouts
    LinearLayout liner;
    // Text
    TextView shop_balance;
    TextView ui_finger_price;
    TextView ui_finger_sum;
    TextView ui_brain_price;
    TextView ui_brain_sum;
    TextView ui_mining_machine_price;
    TextView ui_mining_machine_sum;
    // Cards
    CardView brain;
    CardView finger;
    CardView mining_machine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        setTitle("Магазин");
        initData();

        // Back Save
        SharedPreferences save_load = getSharedPreferences("Saver", MODE_PRIVATE);
        //default
        coins = save_load.getInt("coins", 0);
        shop_balance.setText(save_load.getInt("coins", 0) + Consts.GAME_VALUT);
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

        // Check
        checkMoney();

        // Update UI
        updateUI();
    }

    private void initData() {
        liner = (LinearLayout) findViewById(R.id.shop_for_item);
        shop_balance = (TextView) findViewById(R.id.shop_balance);
        ui_finger_price = (TextView) findViewById(R.id.finger_price);
        ui_finger_sum = (TextView) findViewById(R.id.finger_sum);
        ui_brain_price = (TextView) findViewById(R.id.brain_price);
        ui_brain_sum = (TextView) findViewById(R.id.brain_sum);
        ui_mining_machine_price = (TextView) findViewById(R.id.mining_machine_price);
        ui_mining_machine_sum = (TextView) findViewById(R.id.mining_machine_sum);
        mining_machine = (CardView) findViewById(R.id.mining_machine);
        finger = (CardView) findViewById(R.id.finger);
        brain = (CardView) findViewById(R.id.brain);
    }

    private void updateUI() {
        // Back Save
        SharedPreferences save_load = getSharedPreferences("Saver", MODE_PRIVATE);
        //default
        coins = save_load.getInt("coins", 0);
        shop_balance.setText(save_load.getInt("coins", 0) + Consts.GAME_VALUT);
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

        ui_finger_price.setText(price_finger + Consts.GAME_VALUT);
        ui_finger_sum.setText(sum_finger + " шт.");
        ui_brain_price.setText(price_brain + Consts.GAME_VALUT);
        ui_brain_sum.setText(sum_brain + " шт.");
        ui_mining_machine_price.setText(price_mining_machine + Consts.GAME_VALUT);
        ui_mining_machine_sum.setText(sum_mining_machine + " шт.");
        shop_balance.setText(coins + Consts.GAME_VALUT);

        checkMoney();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.finger:
                if (coins >= price_finger) {
                    if (lvl >= 2) {
                        coins = coins - price_finger;
                        sum_finger = sum_finger + 1;
                        damage = damage + 1;
                        price_finger = price_finger + 1000;
                        SharedPreferences savedProgress = getSharedPreferences("Saver", MODE_PRIVATE);
                        SharedPreferences.Editor editor = savedProgress.edit();
                        editor.putInt("coins", coins);
                        editor.putInt("lvl", lvl);
                        editor.putInt("sum_finger", sum_finger);
                        editor.putInt("price_finger", price_finger);
                        editor.putInt("damage", damage);
                        editor.apply();

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
                        sum_brain = sum_brain + 1;
                        damage = damage + 12;
                        price_brain = price_brain + 7000;

                        SharedPreferences savedProgress = getSharedPreferences("Saver", MODE_PRIVATE);
                        SharedPreferences.Editor editor = savedProgress.edit();
                        editor.putInt("coins", coins);
                        editor.putInt("lvl", lvl);
                        editor.putInt("sum_brain", sum_brain);
                        editor.putInt("price_brain", price_brain);
                        editor.putInt("damage", damage);
                        editor.apply();

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
                        sum_mining_machine = sum_mining_machine + 1;
                        damage = damage + 5;
                        dps = dps + 5;
                        price_mining_machine = price_mining_machine + 20000;

                        SharedPreferences savedProgress = getSharedPreferences("Saver", MODE_PRIVATE);
                        SharedPreferences.Editor editor = savedProgress.edit();
                        editor.putInt("coins", coins);
                        editor.putInt("lvl", lvl);
                        editor.putInt("sum_mining_machine", sum_mining_machine);
                        editor.putInt("price_mining_machine", price_mining_machine);
                        editor.putInt("damage", damage);
                        editor.putInt("dps", dps);
                        editor.apply();

                        updateUI();
                    } else {
                        Toast.makeText(this, "Уровень маловат не потянешь", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Эмм.. не хватает денег", Toast.LENGTH_LONG).show();
                }
        }
    }

    private void checkMoney() {
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
    }
}
