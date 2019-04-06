package com.aksyonov.ettacounter;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_scores extends AppCompatActivity implements View.OnClickListener  {

    TextView tx_total_scores_pl_1,tx_total_scores_pl_2,tx_total_scores_pl_3,tx_total_scores_pl_4;
    TextView tx_best_result_pl_1,tx_best_result_pl_2,tx_best_result_pl_3,tx_best_result_pl_4;

    TextView tx_scores_pl3, tx_scores_pl4, tx_scores_3, tx_scores_4, tx_best_result_3,tx_best_result_4,tx_champ_name;
    TextView tx_scores_pl1, tx_scores_pl2,tx_scores_1,tx_scores_2, tx_best_result_1,tx_best_result_2;

    int qt_player_new_game = Main.qt_player;


    Button bt_scores_main;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        tx_total_scores_pl_1 = (TextView) findViewById(R.id.tx_total_scores_pl_1);
        tx_total_scores_pl_2 = (TextView) findViewById(R.id.tx_total_scores_pl_2);
        tx_total_scores_pl_3 = (TextView) findViewById(R.id.tx_total_scores_pl_3);
        tx_total_scores_pl_4 = (TextView) findViewById(R.id.tx_total_scores_pl_4);

        tx_best_result_pl_1 = (TextView)  findViewById(R.id.tx_best_result_pl_1);
        tx_best_result_pl_2 = (TextView)  findViewById(R.id.tx_best_result_pl_2);
        tx_best_result_pl_3 = (TextView)  findViewById(R.id.tx_best_result_pl_3);
        tx_best_result_pl_4 = (TextView)  findViewById(R.id.tx_best_result_pl_4);

        tx_scores_pl1 = (TextView)  findViewById(R.id.tx_scores_pl1);
        tx_scores_pl2 = (TextView)  findViewById(R.id.tx_scores_pl2);
        tx_scores_pl3 = (TextView)  findViewById(R.id.tx_scores_pl3);
        tx_scores_pl4 = (TextView)  findViewById(R.id.tx_scores_pl4);

        tx_scores_1 = (TextView)  findViewById(R.id.tx_scores_1);
        tx_scores_2 = (TextView)  findViewById(R.id.tx_scores_2);
        tx_scores_3 = (TextView)  findViewById(R.id.tx_scores_3);
        tx_scores_4 = (TextView)  findViewById(R.id.tx_scores_4);

        tx_best_result_1 = (TextView)  findViewById(R.id.tx_best_result_1);
        tx_best_result_2 = (TextView)  findViewById(R.id.tx_best_result_2);
        tx_best_result_3 = (TextView)  findViewById(R.id.tx_best_result_3);
        tx_best_result_4 = (TextView)  findViewById(R.id.tx_best_result_4);

        tx_champ_name = (TextView)  findViewById(R.id.tx_champ_name);

        bt_scores_main = (Button)  findViewById(R.id.bt_scores_main);
        bt_scores_main.setOnClickListener(this);



       if (qt_player_new_game == 3) {
            tx_scores_3.setVisibility(View.VISIBLE);
            tx_scores_pl3.setVisibility(View.VISIBLE);
            tx_best_result_3.setVisibility(View.VISIBLE);
            tx_best_result_pl_3.setVisibility(View.VISIBLE);
            tx_total_scores_pl_3.setVisibility(View.VISIBLE);
        }

        if (qt_player_new_game == 4) {

            tx_scores_3.setVisibility(View.VISIBLE);
            tx_scores_pl3.setVisibility(View.VISIBLE);
            tx_best_result_3.setVisibility(View.VISIBLE);
            tx_best_result_pl_3.setVisibility(View.VISIBLE);
            tx_total_scores_pl_3.setVisibility(View.VISIBLE);

            tx_scores_4.setVisibility(View.VISIBLE);
            tx_scores_pl4.setVisibility(View.VISIBLE);
            tx_best_result_4.setVisibility(View.VISIBLE);
            tx_best_result_pl_4.setVisibility(View.VISIBLE);
            tx_total_scores_pl_4.setVisibility(View.VISIBLE);
        }

        Intent intent5 = getIntent();
        String Champion_name = intent5.getStringExtra("Champion name");
        int Champion_number = Integer.parseInt(intent5.getStringExtra("champion_number"));

        String Pl_1_score = intent5.getStringExtra("Pl_1_score");
        String Pl_2_score = intent5.getStringExtra("Pl_2_score");
        String Pl_3_score = intent5.getStringExtra("Pl_3_score");
        String Pl_4_score = intent5.getStringExtra("Pl_4_score");

        String Pl_1_best_result = intent5.getStringExtra("Pl_1_best_result");
        String Pl_2_best_result = intent5.getStringExtra("Pl_2_best_result");
        String Pl_3_best_result = intent5.getStringExtra("Pl_3_best_result");
        String Pl_4_best_result = intent5.getStringExtra("Pl_4_best_result");


        tx_champ_name.setText(Champion_name);

        tx_total_scores_pl_1.setText(Pl_1_score);
        tx_total_scores_pl_2.setText(Pl_2_score);
        tx_total_scores_pl_3.setText(Pl_3_score);
        tx_total_scores_pl_4.setText(Pl_4_score);

        tx_best_result_pl_1.setText(Pl_1_best_result);
        tx_best_result_pl_2.setText(Pl_2_best_result);
        tx_best_result_pl_3.setText(Pl_3_best_result);
        tx_best_result_pl_4.setText(Pl_4_best_result);

        set_bold_champion(Champion_number);
    }


    private void set_bold_champion( int Champion_number) {

        if (Champion_number == 1) {
        tx_best_result_1.setTypeface(null, Typeface.BOLD);
        tx_scores_1.setTypeface(null, Typeface.BOLD);
        tx_total_scores_pl_1.setTypeface(null, Typeface.BOLD);
        tx_best_result_pl_1.setTypeface(null, Typeface.BOLD);
    }
        if (Champion_number == 2){
            tx_best_result_2.setTypeface(null, Typeface.BOLD);
            tx_scores_2.setTypeface(null, Typeface.BOLD);
            tx_total_scores_pl_2.setTypeface(null, Typeface.BOLD);
            tx_best_result_pl_2.setTypeface(null, Typeface.BOLD);
        }
        if (Champion_number == 3){
            tx_best_result_3.setTypeface(null, Typeface.BOLD);
            tx_scores_3.setTypeface(null, Typeface.BOLD);
            tx_total_scores_pl_3.setTypeface(null, Typeface.BOLD);
            tx_best_result_pl_3.setTypeface(null, Typeface.BOLD);
        }
        if (Champion_number == 4){
            tx_best_result_4.setTypeface(null, Typeface.BOLD);
            tx_scores_4.setTypeface(null, Typeface.BOLD);
            tx_total_scores_pl_4.setTypeface(null, Typeface.BOLD);
            tx_best_result_pl_4.setTypeface(null, Typeface.BOLD);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_scores_main:
                Intent intent6 = new Intent(this, Main.class);
                startActivity(intent6);
                break;


        }
    }
}
