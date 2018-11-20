package com.example.team32gb.jobit.View.Evaluation;

public class RatingMem {
    private String Title;
    private float Rate;

    public RatingMem(String title, float rate) {
        Title = title;
        Rate = rate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public float getRate() {
        return Rate;
    }

    public void setRate(float rate) {
        Rate = rate;
    }
}
