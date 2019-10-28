package com.aksyonov.IotaCounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_start_continue extends AppCompatActivity  implements View.OnClickListener {

    private Button bt_qp_2_str_cont, bt_qp_3str_cont, bt_qp_4_str_cont, bt_continue_game_str_cont, bt_new_game_str_cont;

    private int qt_player_new_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_continue);

        final Button bt_qp_2_str_cont = (Button) findViewById(R.id.bt_qp_2_str_cont);
        final Button bt_qp_3_str_cont = (Button) findViewById(R.id.bt_qp_3_str_cont);
        final Button bt_qp_4_str_cont = (Button) findViewById(R.id.bt_qp_4_str_cont);

        final Button bt_continue_game_str_cont = (Button) findViewById(R.id.bt_continue_game_str_cont);
        final Button bt_new_game_str_cont = (Button) findViewById(R.id.bt_new_game_str_cont);

        bt_qp_2_str_cont.setOnClickListener(this);
        bt_qp_3_str_cont.setOnClickListener(this);
        bt_qp_4_str_cont.setOnClickListener(this);

        bt_continue_game_str_cont.setOnClickListener(this);
        bt_new_game_str_cont.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.bt_qp_2_str_cont:
                Intent intent2 = new Intent(this, Activity_new_game.class);
                setQt_player_new_game(2);
                intent2.putExtra("Qty_pl_in_game", getQt_player_new_game());
                intent2.putExtra("Continue", false);
                startActivity(intent2);
                break;

            case R.id.bt_qp_3_str_cont:
                Intent intent3 = new Intent(this, Activity_new_game.class);
                setQt_player_new_game(3);
                intent3.putExtra("Qty_pl_in_game", getQt_player_new_game());
                intent3.putExtra("Continue", false);

                startActivity(intent3);
                break;

            case R.id.bt_qp_4_str_cont:
                Intent intent4 = new Intent(this, Activity_new_game.class);
                setQt_player_new_game(4);
                intent4.putExtra("Qty_pl_in_game", getQt_player_new_game());
                intent4.putExtra("Continue", false);

                startActivity(intent4);
                break;

            case R.id.bt_continue_game_str_cont:
                Intent intent5 = new Intent(this, Activity_new_game.class);
                setQt_player_new_game(4);
                intent5.putExtra("Qty_pl_in_game", 2);
                intent5.putExtra("Continue", true);
                startActivity(intent5);
                break;
        }


    }

    public int getQt_player_new_game() {
        return qt_player_new_game;
    }

    public void setQt_player_new_game(int qt_player_new_game) {
        this.qt_player_new_game = qt_player_new_game;
    }

}
