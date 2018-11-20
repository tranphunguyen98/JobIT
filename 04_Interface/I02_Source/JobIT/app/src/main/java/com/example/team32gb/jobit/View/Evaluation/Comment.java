package com.example.team32gb.jobit.View.Evaluation;

public class Comment {
    private String Title;
    private int Star;
    private String Date;
    private String Comment;

    public Comment(String title, int star, String date, String comment) {
        Title = title;
        Star = star;
        Date = date;
        Comment = comment;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getStar() {
        return Star;
    }

    public void setStar(int star) {
        Star = star;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
