package com.example.elaboratocookaroo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.elaboratocookaroo.Object.Valutation;
import com.example.elaboratocookaroo.R;

public class CommentAdapter extends ArrayAdapter<Valutation> {
    private Context c;
    private Valutation[] valutations;
    public CommentAdapter(Context c,Valutation[] valutations){
        super(c, R.layout.row_comment,R.id.Username,valutations);
        this.c=c;
        this.valutations=valutations;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) c.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=layoutInflater.inflate(R.layout.row_comment,parent,false);
        TextView username=row.findViewById(R.id.Username);
        RatingBar ratingBar=row.findViewById(R.id.ratingBar);
        TextView comment=row.findViewById(R.id.Comment);
        username.setText(valutations[position].getUsername());
        ratingBar.setRating((float)valutations[position].getGrade());
        comment.setText(valutations[position].getComment());
        return row;
    }
}
