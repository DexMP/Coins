package com.dexmp.dexmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dexmp.dexmp.data.Consts;

public class Profile extends AppCompatActivity{

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
    //Text
    TextView username;
    TextView usersurname;
    TextView bag;
    TextView boosts;
    TextView stats;
    //Images
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Профиль");
        initData();
        loadProgress();
        updateBag();
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

    private void updateBag() {
        bag.setText(coins + Consts.GAME_VALUT + "\n" + lvl + " Уровень" + "\n" + xp + " опыта из " + Consts.X_XP * lvl * 5);

        boosts.setText(
                sum_news + " Вложений новичка" + "\n" +
                sum_finger + " Клик+ куплено" + "\n" +
                sum_brain + " Мозгов куплено" + "\n" +
                sum_mining_machine + " Майнинг машин куплено" +
                sum_mining_farm + " Майнинг ферм куплено"
        );

        stats.setText(dps + " Урона в секунду" + "\n" + damage + " Урона за клик");
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

    private void initData() {
        username = (TextView) findViewById(R.id.name);
        usersurname = (TextView) findViewById(R.id.surname);
        avatar = (ImageView) findViewById(R.id.avatar);
        bag = (TextView) findViewById(R.id.bag);
        boosts = (TextView) findViewById(R.id.boosts);
        stats = (TextView) findViewById(R.id.stats);
    }
}
