package com.example.elaboratocookaroo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.example.elaboratocookaroo.Adapter.CommentAdapter;
import com.example.elaboratocookaroo.Common.Methods;
import com.example.elaboratocookaroo.R;
import com.example.elaboratocookaroo.Request.CommentList;

public class Comment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommentList r=new CommentList("192.168.1.9",5000);
        Methods.SJ(r);
        if(r.getValutations().length!=0){
            setContentView(R.layout.activity_comment);
            ListView listView=findViewById(R.id.lv);
            listView.setAdapter(new CommentAdapter(this,r.getValutations()));
        }
        else {
            setContentView(R.layout.nothingyet);
            TextView textView=findViewById(R.id.message);
            textView.setText("Non ci sono ancora commenti per questo piatto.");
        }
    }
}