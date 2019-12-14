package com.aksyonov.IotaCounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;


public class Settings extends AppCompatActivity {

   private Spinner spinner_time;
   private Button bt_save_game;
   private SwitchCompat sw_hide_result,sw_hard_time;



    private int time_settings;

    private boolean hard_time;



    private boolean hide_result;

    private final String GAME_SETTINGS = "GAME_SETTINGS";
    private final  String TIME_SETTINGS_PREF = "TIME_SETTINGS_PREF";
    private final  String HIDE_CURRENT_RESULT_MODE = "HIDE_CURRENT_RESULT_MODE";
    private final  String HARD_TIMER_MODE = "HARD_TIMER_MODE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        bt_save_game = (Button) findViewById(R.id.bt_save_game);
        spinner_time = (Spinner) findViewById(R.id.spinner_view);

        sw_hide_result = (SwitchCompat) findViewById(R.id.sw_hide_result);
        sw_hard_time = (SwitchCompat) findViewById(R.id.sw_hard_time);


        SharedPreferences prefs = getSharedPreferences(GAME_SETTINGS, MODE_PRIVATE);
        spinner_time.setSelection(prefs.getInt(TIME_SETTINGS_PREF, 0));
        sw_hide_result.setChecked((prefs.getBoolean(HIDE_CURRENT_RESULT_MODE, false)));
        sw_hard_time.setChecked((prefs.getBoolean(HARD_TIMER_MODE, false)));


        bt_save_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete_Settings();
                Save_Settings();
                Toast toast_save = Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT);
                toast_save.setGravity(Gravity.CENTER, 0, 400);
                toast_save.show();

            }

        });

        sw_hide_result.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //   Log.v("LOG_d", ""+isChecked);
                if (isChecked) {
                    setHide_result(isChecked);
                } else {
                    setHide_result(isChecked);
                }
            }


        });


        sw_hard_time.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    setHard_time(isChecked);
                } else {
                    setHard_time(isChecked);
                }
            }


        });
    }

    public   void Save_Settings() {

        setTime_settings(spinner_time.getSelectedItemPosition());


        SharedPreferences.Editor sg = getSharedPreferences(GAME_SETTINGS, MODE_PRIVATE).edit();
        sg.putInt(TIME_SETTINGS_PREF,getTime_settings());
        sg.putBoolean(HIDE_CURRENT_RESULT_MODE,isHide_result());
        sg.putBoolean(HARD_TIMER_MODE,isHard_time());



        sg.commit();

    }



    public int getTime_settings() {
        return time_settings;
    }

    public void setTime_settings(int time_settings) {
        this.time_settings = time_settings;
    }

    public boolean isHard_time() {
        return hard_time;
    }

    public void setHard_time(boolean hard_time) {
        this.hard_time = hard_time;
    }

    public boolean isHide_result() {
        return hide_result;
    }

    public void setHide_result(boolean hide_result) {
        this.hide_result = hide_result;
    }


    public void Delete_Settings(){
        SharedPreferences sh  = getSharedPreferences(GAME_SETTINGS, Context.MODE_PRIVATE);
        sh.edit().clear().commit();
    }


}
