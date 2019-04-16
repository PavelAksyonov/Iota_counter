package com.aksyonov.ettacounter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

// make bold current move


public class Activity_new_game extends AppCompatActivity implements View.OnClickListener {

    EditText next_value;

    Button bt_game_end, bt_ok, bt_skip;


    TextView tx_prev_result_user1,
             tx_prev_result_user2,
             tx_prev_result_user3,
             tx_prev_result_user4,
             tx_pl_1,
             tx_pl_2,
             tx_pl_3,
             tx_pl_4,
             tx_scores_users_1, tx_scores_users_2,tx_current_player,
             tx_scores_users_3, tx_scores_users_4;



    int qt_player_new_game = Main.qt_player;
    int current_user = 1;

    Player Player_1 = new Player("Player_1");
    Player Player_2 = new Player("Player_2");
    Player Player_3= new Player("Player_3");
    Player Player_4 = new Player("Player_4");




    public int get_Qt_player_new_game() {
        return qt_player_new_game;
    }

    public int get_Current_user() {
        return current_user;
    }

    public void set_Current_user(int current_user) {
        this.current_user = current_user;
    }



    public int  getResult() {

        String str = next_value.getText().toString();
        String str2 = new String("");

       // if (str.length() ==0){        }
        if (next_value.getText().length()==0){
            next_value.setError("Please enter result");
        }
        if (str.equals(str2)) {
            return 0;
        } else
            return Integer.parseInt((next_value.getText().toString()));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tx_scores_users_1 = (TextView) findViewById(R.id.tx_scores_users_1);
        tx_scores_users_2 = (TextView) findViewById(R.id.tx_scores_users_2);
        tx_scores_users_3 = (TextView) findViewById(R.id.tx_scores_users_3);
        tx_scores_users_4 = (TextView) findViewById(R.id.tx_scores_users_4);

        tx_pl_1 = (TextView) findViewById(R.id.tx_pl1);
        tx_pl_2 = (TextView) findViewById(R.id.tx_pl2);
        tx_pl_3 = (TextView) findViewById(R.id.tx_pl3);
        tx_pl_4 = (TextView) findViewById(R.id.tx_pl4);

        tx_prev_result_user1 = (TextView) findViewById(R.id.tx_prev_result_user1);
        tx_prev_result_user2 = (TextView) findViewById(R.id.tx_prev_result_user2);
        tx_prev_result_user3 = (TextView) findViewById(R.id.tx_prev_result_user3);
        tx_prev_result_user4 = (TextView) findViewById(R.id.tx_prev_result_user4);

        next_value = (EditText) findViewById(R.id.ed_tx_next_value);
        bt_ok = (Button) findViewById(R.id.bt_ok);
        bt_skip = (Button) findViewById(R.id.bt_skip);
        bt_game_end = (Button) findViewById(R.id.bt_game_end);

        tx_current_player = (TextView) findViewById(R.id.tx_current_player);

        bt_ok.setOnClickListener(this);
        bt_skip.setOnClickListener(this);
        next_value.setOnClickListener(this);
        bt_game_end.setOnClickListener(this);


        if (qt_player_new_game == 3) {
            tx_pl_3.setVisibility(View.VISIBLE);
            tx_scores_users_3.setVisibility(View.VISIBLE);
            tx_prev_result_user3.setVisibility(View.VISIBLE);
        }

        if (qt_player_new_game == 4) {

            tx_pl_3.setVisibility(View.VISIBLE);
            tx_scores_users_3.setVisibility(View.VISIBLE);
            tx_prev_result_user3.setVisibility(View.VISIBLE);

            tx_pl_4.setVisibility(View.VISIBLE);
            tx_scores_users_4.setVisibility(View.VISIBLE);
            tx_prev_result_user4.setVisibility(View.VISIBLE);
        }

       tx_pl_1.setTypeface(null, Typeface.BOLD);
       tx_scores_users_1.setTypeface(null, Typeface.BOLD);
       tx_prev_result_user1.setTypeface(null, Typeface.BOLD);


    }

/*
    @Override
    protected void  onStart() {
        super.onStart();
        next_value = (EditText) findViewById(R.id.ed_tx_next_value);

        result = next_value.toString();
        tx_scores_users_1.setText(result);
    }
*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_game_end:
                set_champion();
                Intent intent5 = new Intent(this, Activity_scores.class);
                intent5.putExtra("Champion name", get_champion_name());
                intent5.putExtra("champion_number", Integer.toString(get_champion_number()));

                intent5.putExtra("Pl_1_score", Integer.toString(Player_1.getUser_score()));
                intent5.putExtra("Pl_2_score", Integer.toString(Player_2.getUser_score()));
                intent5.putExtra("Pl_3_score", Integer.toString(Player_3.getUser_score()));
                intent5.putExtra("Pl_4_score", Integer.toString(Player_4.getUser_score()));

                intent5.putExtra("Pl_1_best_result", Integer.toString(Player_1.getBest_result()));
                intent5.putExtra("Pl_2_best_result", Integer.toString(Player_2.getBest_result()));
                intent5.putExtra("Pl_3_best_result", Integer.toString(Player_3.getBest_result()));
                intent5.putExtra("Pl_4_best_result", Integer.toString(Player_4.getBest_result()));



                startActivity(intent5);
                break;


            case R.id.bt_ok:
                check_edit_result();

                break;

            case R.id.bt_skip:
                next_user();



                break;
        }
    }

    private void check_edit_result() {

    int is_error =0;

     if (next_value.getText().length()==0){
            next_value.setError("Please enter result");
            is_error =1;
            return ;
        }

     if (Integer.parseInt((next_value.getText().toString())) >1000){
            next_value.setError("Please enter correct result");
            is_error =1;
        }
     if (is_error !=1)  {

        set_result();
        next_user();

    }
    }


    private void next_user() {
        int q = get_Current_user();
        int w = get_Qt_player_new_game();

        if (q == 1){
            set_Current_user(2);
            tx_current_player.setText("2");
            set_bold_current_player(2);
        }
        else if (q == 2 & w ==2 ) {
            set_Current_user(1);
            tx_current_player.setText("1");
            set_bold_current_player(1);
        }

        else if (q == 2 & w >=3  ){
            set_Current_user(3);
            tx_current_player.setText("3");
            set_bold_current_player(3);
        }

        else if (q == 3 & w ==3  ){
            set_Current_user(1);
            tx_current_player.setText("1");
            set_bold_current_player(1);
        }

        else if (q == 3 & w ==4  ){
            set_Current_user(4);
            tx_current_player.setText("4");
            set_bold_current_player(4);
        }
        else if (q == 4){
            set_Current_user(1);
            tx_current_player.setText("1");
            set_bold_current_player(1);
        }


    }


    private void set_result() {

        int c = get_Current_user();

        if (c == 1) {

            Player_1.setUser_score(getResult());
            Player_1.setPrevious_result(getResult());
            Player_1.insert_data(getResult());
            Player_1.setBest_result(getResult());
            tx_scores_users_1.setText(Integer.toString(Player_1.getUser_score()));
            tx_prev_result_user1.setText(Integer.toString(Player_1.getPrevious_result()));
            next_value.setText("");

        } else if (c == 2) {


            Player_2.setUser_score(getResult());
            Player_2.setPrevious_result(getResult());
            Player_2.insert_data(getResult());
            Player_2.setBest_result(getResult());
            tx_scores_users_2.setText(Integer.toString(Player_2.getUser_score()));
            tx_prev_result_user2.setText(Integer.toString(Player_2.getPrevious_result()));
            next_value.setText("");

        } else if (c == 3) {
            Player_3.setUser_score(getResult());
            Player_3.setPrevious_result(getResult());
            Player_3.insert_data(getResult());
            Player_3.setBest_result(getResult());
            tx_scores_users_3.setText(Integer.toString(Player_3.getUser_score()));
            tx_prev_result_user3.setText(Integer.toString(Player_3.getPrevious_result()));
            next_value.setText("");

        } else if (c == 4) {
            Player_4.setUser_score(getResult());
            Player_4.setPrevious_result(getResult());
            Player_4.insert_data(getResult());
            Player_4.setBest_result(getResult());
            tx_scores_users_4.setText(Integer.toString(Player_4.getUser_score()));
            tx_prev_result_user4.setText(Integer.toString(Player_4.getPrevious_result()));
            next_value.setText("");
        }

    }

    private void set_bold_current_player(int c) {
        tx_pl_1.setTypeface(null, Typeface.NORMAL);
        tx_pl_2.setTypeface(null, Typeface.NORMAL);
        tx_pl_3.setTypeface(null, Typeface.NORMAL);
        tx_pl_4.setTypeface(null, Typeface.NORMAL);

        tx_scores_users_1.setTypeface(null, Typeface.NORMAL);
        tx_scores_users_2.setTypeface(null, Typeface.NORMAL);
        tx_scores_users_3.setTypeface(null, Typeface.NORMAL);
        tx_scores_users_4.setTypeface(null, Typeface.NORMAL);

        tx_prev_result_user1.setTypeface(null, Typeface.NORMAL);
        tx_prev_result_user2.setTypeface(null, Typeface.NORMAL);
        tx_prev_result_user3.setTypeface(null, Typeface.NORMAL);
        tx_prev_result_user4.setTypeface(null, Typeface.NORMAL);

        if (c == 1){
            tx_pl_1.setTypeface(null, Typeface.BOLD);
            tx_scores_users_1.setTypeface(null, Typeface.BOLD);
            tx_prev_result_user1.setTypeface(null, Typeface.BOLD);
        }
        else if (c == 2) {
            tx_pl_2.setTypeface(null, Typeface.BOLD);
            tx_scores_users_2.setTypeface(null, Typeface.BOLD);
            tx_prev_result_user2.setTypeface(null, Typeface.BOLD);
        }

        else if (c == 3) {
            tx_pl_3.setTypeface(null, Typeface.BOLD);
            tx_scores_users_3.setTypeface(null, Typeface.BOLD);
            tx_prev_result_user3.setTypeface(null, Typeface.BOLD);
        }

        else if (c == 4) {
                tx_pl_4.setTypeface(null, Typeface.BOLD);
                tx_scores_users_4.setTypeface(null, Typeface.BOLD);
                tx_prev_result_user4.setTypeface(null, Typeface.BOLD);
                }
    }

    private void set_champion(){
        int best_scores;
        int best_result;
        int qtu = get_Qt_player_new_game();

        best_scores = Player_1.getUser_score();
        best_result = Player_1.getBest_result();

        Player_1.setIs_champion(1);

        if  (Player_2.getUser_score() > best_scores) {

            best_scores = Player_2.getUser_score();
            best_result = Player_2.getBest_result();

            Player_1.setIs_champion(0);
            Player_2.setIs_champion(1);
        }

        if (Player_2.getUser_score() == best_scores & Player_2.getBest_result()>best_result) {

            best_scores = Player_2.getUser_score();
            best_result = Player_2.getBest_result();

            Player_1.setIs_champion(0);
            Player_2.setIs_champion(1);
        }

         if (qtu == 3 || qtu ==4) {
            if (Player_3.getUser_score() > best_scores) {

                best_scores = Player_3.getUser_score();
                best_result = Player_3.getBest_result();

                Player_1.setIs_champion(0);
                Player_2.setIs_champion(0);
                Player_3.setIs_champion(1);
            }


              if (Player_3.getUser_score() == best_scores & Player_3.getBest_result()>best_result) {
                Player_1.setIs_champion(0);
                Player_2.setIs_champion(0);
                Player_3.setIs_champion(1);

        }
        }
         if (qtu == 4) {
            if (Player_4.getUser_score() > best_scores) {

                best_scores = Player_4.getUser_score();
                best_result = Player_4.getBest_result();

                Player_1.setIs_champion(0);
                Player_2.setIs_champion(0);
                Player_3.setIs_champion(0);
                Player_4.setIs_champion(1);
            }


             if (Player_4.getUser_score() == best_scores & Player_4.getBest_result()>best_result) {
                Player_1.setIs_champion(0);
                Player_2.setIs_champion(0);
                Player_3.setIs_champion(0);
                Player_4.setIs_champion(1);
        }


    }
}
    private String get_champion_name(){
        if (Player_1.getIs_champion() == 1) {
            return Player_1.getPlayer_name();
        }

        if (Player_2.getIs_champion() == 1) {
            return Player_2.getPlayer_name();
        }
        if (Player_3.getIs_champion() == 1) {
            return Player_3.getPlayer_name();
        }
        if (Player_4.getIs_champion() == 1) {
            return Player_4.getPlayer_name();
        }
        return "Unknown";
    }

    private int get_champion_number(){
        if (Player_1.getIs_champion() == 1) {
            return 1;
        }

        if (Player_2.getIs_champion() == 1) {
            return 2;
        }
        if (Player_3.getIs_champion() == 1) {
            return 3;
        }
        if (Player_4.getIs_champion() == 1) {
            return 4;
        }
        return 0;
    }
}



