package com.aksyonov.ettacounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity implements View.OnClickListener{


   public static int qt_player =0;
   // final String TEG ="lifetest";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button bt_new_game = (Button) findViewById(R.id.bt_new_game);
        final Button bt_best_scores = (Button) findViewById(R.id.bt_best_scores);

        final TextView tx_qp = (TextView) findViewById(R.id.tx_qp);

        final Button bt_qp_2 = (Button) findViewById(R.id.bt_qp_2);
        final Button bt_qp_3 = (Button) findViewById(R.id.bt_qp_3);
        final Button bt_qp_4 = (Button) findViewById(R.id.bt_qp_4);



        final TextView tx_scores_users_1 = (TextView) findViewById(R.id.tx_scores_users_1);
        final TextView tx_scores_users_2 = (TextView) findViewById(R.id.tx_scores_users_2);


        final TextView tx_round_user1 = (TextView) findViewById(R.id.tx_prev_result_user1);
        final TextView tx_round_user2 = (TextView) findViewById(R.id.tx_prev_result_user2);






        bt_best_scores.setOnClickListener(this);

        bt_qp_2.setOnClickListener(this);
        bt_qp_3.setOnClickListener(this);
        bt_qp_4.setOnClickListener(this);

       // tx_pl_3.setOnClickListener(this);


        bt_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   tx_qp.setVisibility(View.VISIBLE);
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
                Intent intent = new Intent(this, Activity_settings.class);
                startActivity(intent);
                break;

            case R.id.bt_qp_2:
                Intent intent2 = new Intent(this, Activity_new_game.class);
                startActivity(intent2);
                qt_player=2;
              //  Log.d(TEG, "onCreate" );
                break;

          case R.id.bt_qp_3:
                Intent intent3 = new Intent(this, Activity_new_game.class);
                startActivity(intent3);
                qt_player=3;
               // new Activity_new_game().getOne();
               // int a = Activity_new_game.one;
                break;

            case R.id.bt_qp_4:
                Intent intent4 = new Intent(this, Activity_new_game.class);
                startActivity(intent4);
                qt_player=4;
                break;



                default:
                    break;
        }
    }
}
