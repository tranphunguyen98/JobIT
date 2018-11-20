package com.example.team32gb.jobit.View.Evaluation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.team32gb.jobit.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Rating extends Fragment {
    private ListView lstRatingMem;
    private ListView lstComment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_rating,null);
        return view;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        lstRatingMem=getView().findViewById(R.id.lstRatingMem);
        ArrayList<RatingMem> arrRatingMem=new ArrayList<RatingMem>();

        RatingMem rt1= new RatingMem("Salary",1);
        RatingMem rt2=new RatingMem("Environment",2);
        RatingMem rt3=new RatingMem("Pressure",3);

        arrRatingMem.add(rt1);
        arrRatingMem.add(rt2);
        arrRatingMem.add(rt3);

        CustomRatingMemAdapter customRatingMemAdapter = new CustomRatingMemAdapter(this.getContext(),arrRatingMem);
        lstRatingMem.setAdapter(customRatingMemAdapter);

        lstComment=getView().findViewById(R.id.lstComment);
        ArrayList<Comment> arrComment=new ArrayList<>();

        Comment cmt1=new Comment("Trust issue",4,"10/10/2018","OK");
        Comment cmt2=new Comment("Hesitation",1,"10/10/2018","Bad");
        Comment cmt3=new Comment("Conversation",2,"10/10/2018","Worse");

        arrComment.add(cmt1);
        arrComment.add(cmt2);
        arrComment.add(cmt3);

        CustomCommentAdapter customCommentAdapter= new CustomCommentAdapter(this.getContext(),arrComment);
        lstComment.setAdapter(customCommentAdapter);
    }
}
