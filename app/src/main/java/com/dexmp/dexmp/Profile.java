package com.dexmp.dexmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    // Vars
    protected int coins;
    protected int xp;
    protected int lvl;
    protected int price_finger;
    protected int sum_finger;
    protected int price_brain;
    protected int sum_brain;
    protected int dps;
    protected int damage;

    // UI
    TextView username;
    TextView usersurname;
    TextView bag;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Профиль");
        initData();

        // Back Save
        SharedPreferences save_load = getSharedPreferences("Saver", MODE_PRIVATE);
        //default
        coins = save_load.getInt("coins", 0);
        xp = save_load.getInt("xp", 0);
        lvl = save_load.getInt("lvl", 0);
        //finger
        price_finger = save_load.getInt("price_finger", Consts.START_PRICE_RAM);
        sum_finger = save_load.getInt("sum_finger", 0);
        //brain
        price_brain = save_load.getInt("price_brain", Consts.START_PRICE_BRAIN);
        sum_brain = save_load.getInt("sum_brain", 0);
        //full damage
        dps = save_load.getInt("dps", 0);
        damage = save_load.getInt("damage", Consts.START_DAMAGE);

        bag.setText(coins + Consts.GAME_VALUT + "\n" +
                xp + " Опыта" + "\n" + lvl + " Уровень" + "\n" +
                sum_finger + " Пальцев куплено" + "\n" +
                sum_brain + " Мозгов куплено" + "\n" +
                dps + " Урона в секунду" + "\n" +
                damage + " Урона за клик" + "\n");
    }

    private void initData() {
        username = (TextView) findViewById(R.id.name);
        usersurname = (TextView) findViewById(R.id.surname);
        avatar = (ImageView) findViewById(R.id.avatar);
        bag = (TextView) findViewById(R.id.all_stats);
    }
}
