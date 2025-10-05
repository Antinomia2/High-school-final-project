package com.example.elaboratocookaroo.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.example.elaboratocookaroo.R;

import androidx.appcompat.app.AppCompatDialogFragment;

public class MessageDialog extends AppCompatDialogFragment {
    private String title,content;
    public MessageDialog(String title,String content){
        this.title=title;
        this.content=content;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstaceState){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_message,null);
        TextView tv=view.findViewById(R.id.content);
        tv.setText(content);
        builder.setView(view).setTitle(title).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        return builder.create();
    }
}
