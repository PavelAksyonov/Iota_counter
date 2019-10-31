package com.aksyonov.IotaCounter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Activity_new_game extends AppCompatActivity implements View.OnClickListener {

    private  EditText edit_result;

    private Button bt_game_end, bt_ok, bt_skip;


    private TextView
             tx_prev_result_user1, tx_prev_result_user2, tx_prev_result_user3, tx_prev_result_user4,
             tx_pl_1, tx_pl_2, tx_pl_3, tx_pl_4,
             tx_scores_users_1, tx_scores_users_2,tx_scores_users_3, tx_scores_users_4,
             tx_current_player;




    private int qt_player_in_new_game;
    private int current_user = 1;


    private boolean game_over = false;

    Player Player_1 = new Player("Player_1");
    Player Player_2 = new Player("Player_2");
    Player Player_3 = new Player("Player_3");
    Player Player_4 = new Player("Player_4");

    private DataBase dataBase;


    public   void SaveGame() {
        int qty = getQt_player_in_new_game();
        int current_user =get_Current_user();

        SharedPreferences.Editor sg = getSharedPreferences("SAVE GAME", MODE_PRIVATE).edit();
        sg.putInt("qt_players_pref",qty);
        sg.putInt("current_user_pref",current_user);
        sg.putBoolean("GAME_IS_OVER",false);

        sg.putString("user_scores_pl_1_pref",Integer.toString(Player_1.getUser_score()));
        sg.putString("user_scores_pl_2_pref",Integer.toString(Player_2.getUser_score()));

        sg.putString("user_best_score_pl_1_pref",Integer.toString(Player_1.getBest_result()));
        sg.putString("user_best_score_pl_2_pref",Integer.toString(Player_2.getBest_result()));
        sg.putString("user_prev_score_pl_1_pref",Integer.toString(Player_1.getPrevious_result()));
        sg.putString("user_prev_score_pl_2_pref",Integer.toString(Player_2.getPrevious_result()));

        if(qty ==3){
            sg.putString("user_scores_pl_3_pref",Integer.toString(Player_3.getUser_score()));
            sg.putString("user_best_score_pl_3_pref",Integer.toString(Player_3.getBest_result()));
            sg.putString("user_prev_score_pl_3_pref",Integer.toString(Player_3.getPrevious_result()));
                   }

        if(qty ==4){
            sg.putString("user_scores_pl_3_pref",Integer.toString(Player_3.getUser_score()));
            sg.putString("user_scores_pl_4_pref",Integer.toString(Player_4.getUser_score()));
            sg.putString("user_best_score_pl_3_pref",Integer.toString(Player_3.getBest_result()));
            sg.putString("user_best_score_pl_4_pref",Integer.toString(Player_4.getBest_result()));
            sg.putString("user_prev_score_pl_3_pref",Integer.toString(Player_3.getPrevious_result()));
            sg.putString("user_prev_score_pl_4_pref",Integer.toString(Player_4.getPrevious_result()));
        }


        sg.commit();

    }

    public  void LoadGame() {
        SharedPreferences prefs = getSharedPreferences("SAVE GAME", MODE_PRIVATE);

        int qty =prefs.getInt("qt_players_pref",2);
        int current_user =prefs.getInt("current_user_pref",1);


        tx_current_player.setText(Integer.toString(current_user));
        set_bold_current_player(current_user);
        setQt_player_in_new_game(qty);


        Player_1.setUser_score_loading(Integer.parseInt(prefs.getString("user_scores_pl_1_pref","0")));
        Player_2.setUser_score_loading(Integer.parseInt(prefs.getString("user_scores_pl_2_pref","0")));

        Player_1.setPrevious_result_loading(Integer.parseInt(prefs.getString("user_prev_score_pl_1_pref","0")));
        Player_2.setPrevious_result_loading(Integer.parseInt(prefs.getString("user_prev_score_pl_2_pref","0")));

        Player_1.setBest_result_loading(Integer.parseInt(prefs.getString("user_best_score_pl_1_pref","0")));
        Player_2.setBest_result_loading(Integer.parseInt(prefs.getString("user_best_score_pl_2_pref","0")));

        set_bold_current_player(current_user);
        set_Current_user(current_user);

        // refresh view
        tx_current_player.setText(Integer.toString(current_user));

        tx_scores_users_1.setText(prefs.getString("user_scores_pl_1_pref","0"));
        tx_scores_users_2.setText(prefs.getString("user_scores_pl_2_pref","0"));

        tx_prev_result_user1.setText(prefs.getString("user_prev_score_pl_1_pref","0"));
        tx_prev_result_user2.setText(prefs.getString("user_prev_score_pl_2_pref","0"));

        if(qty ==3){
            Player_3.setUser_score_loading(Integer.parseInt(prefs.getString("user_scores_pl_3_pref","0")));
            Player_3.setPrevious_result_loading(Integer.parseInt(prefs.getString("user_prev_score_pl_3_pref","0")));
            Player_3.setBest_result_loading(Integer.parseInt(prefs.getString("user_best_score_pl_3_pref","0")));

            tx_scores_users_3.setText(prefs.getString("user_scores_pl_3_pref","0"));
            tx_prev_result_user3.setText(prefs.getString("user_prev_score_pl_3_pref","0"));

            tx_pl_3.setVisibility(View.VISIBLE);
            tx_scores_users_3.setVisibility(View.VISIBLE);
            tx_prev_result_user3.setVisibility(View.VISIBLE);
        }

        if(qty ==4){
            Player_3.setUser_score_loading(Integer.parseInt(prefs.getString("user_scores_pl_3_pref","0")));
            Player_3.setPrevious_result_loading(Integer.parseInt(prefs.getString("user_prev_score_pl_3_pref","0")));
            Player_3.setBest_result_loading(Integer.parseInt(prefs.getString("user_best_score_pl_3_pref","0")));

            Player_4.setUser_score_loading(Integer.parseInt(prefs.getString("user_scores_pl_4_pref","0")));
            Player_4.setPrevious_result_loading(Integer.parseInt(prefs.getString("user_prev_score_pl_4_pref","0")));
            Player_4.setBest_result_loading(Integer.parseInt(prefs.getString("user_best_score_pl_4_pref","0")));

            tx_scores_users_3.setText(prefs.getString("user_scores_pl_3_pref","0"));
            tx_scores_users_4.setText(prefs.getString("user_scores_pl_4_pref","0"));

            tx_prev_result_user3.setText(prefs.getString("user_prev_score_pl_3_pref","0"));
            tx_prev_result_user4.setText(prefs.getString("user_prev_score_pl_4_pref","0"));

            tx_pl_3.setVisibility(View.VISIBLE);
            tx_scores_users_3.setVisibility(View.VISIBLE);
            tx_prev_result_user3.setVisibility(View.VISIBLE);

            tx_pl_4.setVisibility(View.VISIBLE);
            tx_scores_users_4.setVisibility(View.VISIBLE);
            tx_prev_result_user4.setVisibility(View.VISIBLE);
        }

    }


public void DeleteSavedGame(){

    SharedPreferences sh  = getSharedPreferences("SAVE GAME", Context.MODE_PRIVATE);
    sh.edit().clear().commit();

}


    public int get_Qt_player_new_game() {
        return qt_player_in_new_game;
    }

    public int get_Current_user() {
        return current_user;
    }

    public void set_Current_user(int current_user) {
        this.current_user = current_user;
    }



    public int  getResult() {

        String str = edit_result.getText().toString().trim();
        String str2 = new String("");

        if (edit_result.getText().length()==0){
            edit_result.setError("Please enter result");
        }
        if (str.equals(str2)) {
            return 0;
        } else
            return Integer.parseInt((edit_result.getText().toString()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(!get_game_over()){
            SaveGame();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(!get_game_over()){
            SaveGame();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(!get_game_over()) {
            LoadGame();
        }

    }

    @Override
    public void onBackPressed() {
        return;
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

        edit_result = (EditText) findViewById(R.id.ed_tx_next_value);
        bt_ok = (Button) findViewById(R.id.bt_ok);
        bt_skip = (Button) findViewById(R.id.bt_skip);


        bt_game_end = (Button) findViewById(R.id.bt_game_end);

        tx_current_player = (TextView) findViewById(R.id.tx_current_player);

        bt_ok.setOnClickListener(this);
        bt_skip.setOnClickListener(this);
        edit_result.setOnClickListener(this);
        bt_game_end.setOnClickListener(this);

       tx_pl_1.setTypeface(null, Typeface.BOLD);
       tx_scores_users_1.setTypeface(null, Typeface.BOLD);
       tx_prev_result_user1.setTypeface(null, Typeface.BOLD);


// enter result from button android enter
        edit_result.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            check_edit_result();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });


        dataBase = new DataBase(this);

        Intent intent = getIntent();
        setQt_player_in_new_game(intent.getExtras().getInt("Qty_pl_in_game", 2));

        boolean cont = intent.getExtras().getBoolean("Continue", false);
        if (cont) {

            LoadGame();
        }

        if (getQt_player_in_new_game() == 3) {
            tx_pl_3.setVisibility(View.VISIBLE);
            tx_scores_users_3.setVisibility(View.VISIBLE);
            tx_prev_result_user3.setVisibility(View.VISIBLE);
        }

        if (getQt_player_in_new_game() == 4) {

            tx_pl_3.setVisibility(View.VISIBLE);
            tx_scores_users_3.setVisibility(View.VISIBLE);
            tx_prev_result_user3.setVisibility(View.VISIBLE);

            tx_pl_4.setVisibility(View.VISIBLE);
            tx_scores_users_4.setVisibility(View.VISIBLE);
            tx_prev_result_user4.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View v) {

        SQLiteDatabase database = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {
            case R.id.bt_game_end:


             DeleteSavedGame();
             set_game_over(true);
             set_champion();

                //insert data
                if (get_champion_result() > 0) {

                database.beginTransaction();

                try {
                    contentValues.put(ScoresTable.ScoresEntry.KEY_PLAYER_NAME, get_champion_name());
                    contentValues.put(ScoresTable.ScoresEntry.KEY_RESULT, get_champion_result());
                    contentValues.put(ScoresTable.ScoresEntry.KEY_QUANTITY_PLAYERS, get_Qt_player_new_game());
                    contentValues.put(ScoresTable.ScoresEntry.KEY_DATE, get_current_date());

                    database.insert(DataBase.TABLE_SCORES, null, contentValues);

                    database.setTransactionSuccessful();
                } finally {
                    database.endTransaction();
                }
                }



                Intent intent5 = new Intent(this, Activity_current_scores.class);

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

                intent5.putExtra("Qty_pl_in_game", Integer.toString(getQt_player_in_new_game()));
                startActivity(intent5);
                break;



            case R.id.bt_ok:
                check_edit_result();


                break;

            case R.id.bt_skip:
                set_scip_result();
                next_user();



                //database.delete(DataBase.TABLE_SCORES, null, null);


                break;
        }

        dataBase.close();
    }

    private void check_edit_result() {

    int is_error =0;

     if (edit_result.getText().length()==0){
            edit_result.setError("Please enter result");
         is_error =1;
            vibro_error();
            return ;
        }

     if (Integer.parseInt((edit_result.getText().toString())) >1000){
            edit_result.setError("Please enter correct result");
            is_error =1;
            vibro_error();
            return ;
        }
     if (is_error !=1)  {

        set_result();
        next_user();

    }
    }

    private void vibro_error() {

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(250L);
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
            edit_result.setText("");

        } else if (c == 2) {


            Player_2.setUser_score(getResult());
            Player_2.setPrevious_result(getResult());
            Player_2.insert_data(getResult());
            Player_2.setBest_result(getResult());
            tx_scores_users_2.setText(Integer.toString(Player_2.getUser_score()));
            tx_prev_result_user2.setText(Integer.toString(Player_2.getPrevious_result()));
            edit_result.setText("");

        } else if (c == 3) {
            Player_3.setUser_score(getResult());
            Player_3.setPrevious_result(getResult());
            Player_3.insert_data(getResult());
            Player_3.setBest_result(getResult());
            tx_scores_users_3.setText(Integer.toString(Player_3.getUser_score()));
            tx_prev_result_user3.setText(Integer.toString(Player_3.getPrevious_result()));
            edit_result.setText("");

        } else if (c == 4) {
            Player_4.setUser_score(getResult());
            Player_4.setPrevious_result(getResult());
            Player_4.insert_data(getResult());
            Player_4.setBest_result(getResult());
            tx_scores_users_4.setText(Integer.toString(Player_4.getUser_score()));
            tx_prev_result_user4.setText(Integer.toString(Player_4.getPrevious_result()));
            edit_result.setText("");
        }

    }

    private void set_scip_result() {

        int c = get_Current_user();
        String scip ="0";

        if (c == 1) {

            Player_1.setUser_score(0);
            Player_1.setPrevious_result(0);
            Player_1.insert_data(0);
            Player_1.setBest_result(0);
            tx_scores_users_1.setText(Integer.toString(Player_1.getUser_score()));
            tx_prev_result_user1.setText(scip);
            edit_result.setText("");

        } else if (c == 2) {


            Player_2.setUser_score(0);
            Player_2.setPrevious_result(0);
            Player_2.insert_data(0);
            Player_2.setBest_result(0);
            tx_scores_users_2.setText(Integer.toString(Player_2.getUser_score()));
            tx_prev_result_user2.setText(scip);
            edit_result.setText("");

        } else if (c == 3) {
            Player_3.setUser_score(0);
            Player_3.setPrevious_result(0);
            Player_3.insert_data(0);
            Player_3.setBest_result(0);
            tx_scores_users_3.setText(Integer.toString(Player_3.getUser_score()));
            tx_prev_result_user3.setText(scip);
            edit_result.setText("");

        } else if (c == 4) {
            Player_4.setUser_score(0);
            Player_4.setPrevious_result(0);
            Player_4.insert_data(0);
            Player_4.setBest_result(0);
            tx_scores_users_4.setText(Integer.toString(Player_3.getUser_score()));
            tx_prev_result_user4.setText(scip);
            edit_result.setText("");
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

    private int get_champion_result(){
        if (Player_1.getIs_champion() == 1) {
            return Player_1.getUser_score();
        }

        if (Player_2.getIs_champion() == 1) {
            return Player_2.getUser_score();
        }
        if (Player_3.getIs_champion() == 1) {
            return Player_3.getUser_score();
        }
        if (Player_4.getIs_champion() == 1) {
            return Player_4.getUser_score();
        }
        return 0;
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

    public String get_current_date (){
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
         String currentDateText = dateFormat.format(currentDate);
        return currentDateText;
    }

    public int getQt_player_in_new_game() {
        return qt_player_in_new_game;
    }

    public void setQt_player_in_new_game(int qt_player_in_new_game) {
        this.qt_player_in_new_game = qt_player_in_new_game;
    }

    public boolean get_game_over() {
        return game_over;
    }

    public void set_game_over(boolean game_over) {
        this.game_over = game_over;
    }

}



