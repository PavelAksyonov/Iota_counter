package com.aksyonov.IotaCounter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class Activity_top_score extends AppCompatActivity  implements View.OnClickListener{


    private SQLiteDatabase mDatabase;
    private ScoreAdapter mAdapter;

    private Button bt_top_score_to_main;

    public SharedPref sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_score);

        DataBase dbHelper = new DataBase(this);
        mDatabase =dbHelper.getWritableDatabase();

        RecyclerView recyclerView =findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ScoreAdapter(this, getAllScores());
        recyclerView.setAdapter(mAdapter);

        bt_top_score_to_main = (Button)  findViewById(R.id.bt_top_score_to_main);
        bt_top_score_to_main.setOnClickListener(this);

        //sharedPref.LoadGame();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_top_score_to_main:
                Intent intent6 = new Intent(this, Activity_start.class);
                startActivity(intent6);
                break;


        }
    }


    private Cursor getAllScores() {
        return mDatabase.query(
                DataBase.TABLE_SCORES,
                null,
                null,
                null,
                null,
                null,
                ScoresTable.ScoresEntry.KEY_RESULT + " DESC"
        );
}

}
