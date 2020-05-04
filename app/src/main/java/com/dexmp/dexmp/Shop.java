package com.dexmp.dexmp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Shop extends AppCompatActivity {

    // Vars
    protected int coins;
    protected int xp;
    protected int lvl;

    // UI
    LinearLayout liner;
    TextView shop_balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initData();

        // Back Save
        SharedPreferences save_load = getSharedPreferences("Saver", MODE_PRIVATE);
        coins = save_load.getInt("coins", 0);
        shop_balance.setText(save_load.getInt("coins", 0) + " Oids");
        xp = save_load.getInt("xp", 0);
        lvl = save_load.getInt("lvl", 0);

    }

    private void initData() {
        liner = (LinearLayout) findViewById(R.id.shop_for_item);
        shop_balance = (TextView) findViewById(R.id.shop_balance);
    }
}
