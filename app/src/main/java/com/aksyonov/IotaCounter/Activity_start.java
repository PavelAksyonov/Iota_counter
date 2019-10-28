package com.aksyonov.IotaCounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Activity_start extends AppCompatActivity implements View.OnClickListener{

   private Button bt_qp_2, bt_qp_3, bt_qp_4;


   private int qt_player_in_game =2;
   private String GAME_IS_OVER;

    public  boolean CheckisLoadGame() {
        boolean  go;

            SharedPreferences prefs = getSharedPreferences("SAVE GAME", MODE_PRIVATE);
            go=prefs.getBoolean(GAME_IS_OVER,true);
            Log.i("LOG_d", Boolean.toString(go));
            return go;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        final Button bt_new_game = (Button) findViewById(R.id.bt_new_game);
        final Button bt_best_scores = (Button) findViewById(R.id.bt_best_scores);

        final Button bt_qp_2 = (Button) findViewById(R.id.bt_qp_2);
        final Button bt_qp_3 = (Button) findViewById(R.id.bt_qp_3);
        final Button bt_qp_4 = (Button) findViewById(R.id.bt_qp_4);


        final TextView tx_round_user1 = (TextView) findViewById(R.id.tx_prev_result_user1);
        final TextView tx_round_user2 = (TextView) findViewById(R.id.tx_prev_result_user2);


        bt_best_scores.setOnClickListener(this);

        bt_qp_2.setOnClickListener(this);
        bt_qp_3.setOnClickListener(this);
        bt_qp_4.setOnClickListener(this);


        // если есть не сохраненная игра, предлагаем продолжить

            if (!CheckisLoadGame()){
              //   Intent intent5 = new Intent(this, Activity_start_continue.class);
             //   startActivity(intent5);
                Log.i("LOG_d", Boolean.toString(CheckisLoadGame()));
              }


        bt_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast_ch_qty = Toast.makeText(Activity_start.this, "Choose quantity players", Toast.LENGTH_SHORT);
                toast_ch_qty.show();

                   bt_qp_2.setVisibility(View.VISIBLE);
                   bt_qp_3.setVisibility(View.VISIBLE);
                   bt_qp_4.setVisibility(View.VISIBLE);
            }

        });

}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           case R.id.bt_best_scores:
                Intent intent = new Intent(this, Activity_top_score.class);
                startActivity(intent);
                break;

            case R.id.bt_qp_2:
                Intent intent2 = new Intent(this, Activity_new_game.class);
                setQt_player_in_game(2);
                intent2.putExtra("Qty_pl_in_game", getQt_player_in_game());
                startActivity(intent2);

                break;

          case R.id.bt_qp_3:
                Intent intent3 = new Intent(this, Activity_new_game.class);
                setQt_player_in_game(3);
                intent3.putExtra("Qty_pl_in_game", getQt_player_in_game());
                startActivity(intent3);
                break;

            case R.id.bt_qp_4:
                Intent intent4 = new Intent(this, Activity_new_game.class);
                setQt_player_in_game(4);
                intent4.putExtra("Qty_pl_in_game", getQt_player_in_game());
                startActivity(intent4);
                break;

        }
    }

    public int getQt_player_in_game() {
        return qt_player_in_game;
    }

    public void setQt_player_in_game(int qt_player_in_game) {
        this.qt_player_in_game = qt_player_in_game;
    }

    public void DeleteSavedGame(){

        SharedPreferences sh  = getSharedPreferences("SAVE GAME", Context.MODE_PRIVATE);
        sh.edit().clear().commit();

    }
}
