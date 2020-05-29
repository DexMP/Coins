package com.dexmp.dexmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity implements View.OnClickListener {

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
    EditText code;
    ImageView send;
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

        updateBag();

        send.setOnClickListener(this);
    }

    private void initData() {
        username = (TextView) findViewById(R.id.name);
        usersurname = (TextView) findViewById(R.id.surname);
        avatar = (ImageView) findViewById(R.id.avatar);
        bag = (TextView) findViewById(R.id.all_stats);
        code = (EditText) findViewById(R.id.str_for_code);
        send = (ImageView) findViewById(R.id.send_code);
    }

    private void updateBag() {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_code:
                if (code.getText().toString().contains("getadmin3000")) {
                    coins = coins + 3000;
                    SharedPreferences savedProgress = getSharedPreferences("Saver", MODE_PRIVATE);
                    SharedPreferences.Editor editor = savedProgress.edit();
                    editor.putInt("coins", coins);
                    editor.apply();
                    updateBag();
                    code.setText("");
                } else if (code.getText().toString().contains("getdex10000")) {
                    coins = coins + 10000;
                    SharedPreferences savedProgress = getSharedPreferences("Saver", MODE_PRIVATE);
                    SharedPreferences.Editor editor = savedProgress.edit();
                    editor.putInt("coins", coins);
                    editor.apply();
                    updateBag();
                    code.setText("");
                } else if (code.getText().toString().contains("superadmin")) {
                    coins = coins + 100000;
                    SharedPreferences savedProgress = getSharedPreferences("Saver", MODE_PRIVATE);
                    SharedPreferences.Editor editor = savedProgress.edit();
                    editor.putInt("coins", coins);
                    editor.apply();
                    updateBag();
                    code.setText("");
                }
                else {
                    code.setText("");
                    Toast.makeText(this, "Плохая попытка!", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
