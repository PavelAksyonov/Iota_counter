package com.aksyonov.IotaCounter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScoreAdapter extends RecyclerView.Adapter <ScoreAdapter.ScoreViewHolder> {

    private Context mContext;
    private Cursor mCursor;


    public ScoreAdapter (Context context, Cursor cursor ){
        mContext = context;
        mCursor = cursor;
    }


    public class ScoreViewHolder extends RecyclerView.ViewHolder{

        public TextView scoreName;
        public TextView scoreResult;
        public TextView scoreDate;
        public TextView scoreQtyPlayers;

        public ScoreViewHolder(View itemView) {
            super(itemView);

            scoreName = itemView.findViewById(R.id.tv_score_name_item);
            scoreResult = itemView.findViewById(R.id.tv_score_result_item);
            scoreDate = itemView.findViewById(R.id.tv_score_date_item);
            scoreQtyPlayers = itemView.findViewById(R.id.tv_qty_player_item);

        }
    }

    @Override
    public ScoreViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view =inflater.inflate(R.layout.score_item, parent, false);
        return new ScoreViewHolder(view);

    }

    @Override
    public void onBindViewHolder( ScoreViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }

         String Pl_name =mCursor.getString(mCursor.getColumnIndex(ScoresTable.ScoresEntry.KEY_PLAYER_NAME));
         String Pl_result =mCursor.getString(mCursor.getColumnIndex(ScoresTable.ScoresEntry.KEY_RESULT));
         String Pl_date =mCursor.getString(mCursor.getColumnIndex(ScoresTable.ScoresEntry.KEY_DATE));
         String Pl_Qty_players =mCursor.getString(mCursor.getColumnIndex(ScoresTable.ScoresEntry.KEY_QUANTITY_PLAYERS));

         holder.scoreName.setText(Pl_name);
         holder.scoreResult.setText(Pl_result);
         holder.scoreDate.setText(Pl_date);
         holder.scoreQtyPlayers.setText(String.valueOf(Pl_Qty_players));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}


