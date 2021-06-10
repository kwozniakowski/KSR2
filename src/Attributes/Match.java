package Attributes;

import java.util.ArrayList;

public class Match {
    private String id;
    private float winner_rank;
    private float loser_rank;
    private float winner_age;
    private float w_ace;
    private float l_ace;
    private float w_df;
    private float l_df;
    private float w_1stIn;
    private float winner_ht;
    private float minutes;

    public Match(String id, float winner_rank, float loser_rank, float winner_age, float w_ace, float l_ace, float w_df, float l_df, float w_1stIn, float winner_ht, float minutes) {
        this.id = id;
        this.winner_rank = winner_rank;
        this.loser_rank = loser_rank;
        this.winner_age = winner_age;
        this.w_ace = w_ace;
        this.l_ace = l_ace;
        this.w_df = w_df;
        this.l_df = l_df;
        this.w_1stIn = w_1stIn;
        this.winner_ht = winner_ht;
        this.minutes = minutes;
    }

    public float getWinner_rank() {
        return winner_rank;
    }

    public float getLoser_rank() {
        return loser_rank;
    }

    public float getWinner_age() {
        return winner_age;
    }

    public float getW_ace() {
        return w_ace;
    }

    public float getL_ace() {
        return l_ace;
    }

    public float getW_df() {
        return w_df;
    }

    public float getL_df() {
        return l_df;
    }

    public float getW_1stIn() {
        return w_1stIn;
    }

    public float getWinner_ht() {
        return winner_ht;
    }

    public float getMinutes() {
        return minutes;
    }

    public float getMatchAttribute(String name)
    {
        if(name.equals("Ranking zwyciezcy")) return winner_rank;
        if(name.equals("Ranking przegranego")) return loser_rank;
        if(name.equals("Wiek zwyciezcy")) return winner_age;
        if(name.equals("Liczba asow zwyciezcy")) return w_ace;
        if(name.equals("Liczba asow przegranego")) return l_ace;
        if(name.equals("Liczba DF zwyciezcy")) return w_df;
        if(name.equals("Liczba DF przegranego")) return l_df;
        if(name.equals("Wzrost zwyciezcy")) return winner_ht;
        if(name.equals("Procent pierwszego serwisu zwyciezcy")) return w_1stIn;
        if(name.equals("Dlugosc meczu")) return minutes;
        else return 0;
    }
}
