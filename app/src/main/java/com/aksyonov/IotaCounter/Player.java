package com.aksyonov.IotaCounter;

import java.util.ArrayList;

public class Player {

   public int score =0;
   public int previous_result =0;
   public int best_result =0;
   public  String player_name;




   public int is_champion =0;



    public int getIs_champion() {
        return is_champion;
    }

    public void setIs_champion(int is_champion) {
        this.is_champion = is_champion;
    }



    public Player(String player_name) {
        this.player_name = player_name;
    }

    public int getUser_score() {
        return score;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public void setUser_score(int current_score) {
        this.score += current_score;
    }

    public int getPrevious_result() {

        return previous_result;
    }

    public void setPrevious_result(int previous_result) {
        this.previous_result = previous_result;
    }

    public ArrayList <Integer> Data_game = new ArrayList<>(5);


    public void insert_data (int result){
        Data_game.add(result);
    }


    public int getBest_result() {
        return best_result;
    }

    public void setBest_result(int result) {
        if (result > this.best_result)
        {
            this.best_result = result;
        }
    }



}
