package com.example.elaboratocookaroo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.elaboratocookaroo.Common.Methods;
import com.example.elaboratocookaroo.Dialog.MessageDialog;
import com.example.elaboratocookaroo.Object.RegisterInfo;
import com.example.elaboratocookaroo.R;
import com.example.elaboratocookaroo.Request.Register;
import com.google.android.gms.common.internal.service.Common;

public class RegisterActivity extends AppCompatActivity {
    private EditText id;
    private EditText name;
    private EditText lastname;
    private EditText phone;
    private EditText indirizzo;
    private EditText pass;
    private TextView Login;
    private CardView Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        id=findViewById(R.id.ID);
        name=findViewById(R.id.Name);
        lastname=findViewById(R.id.LastName);
        phone=findViewById(R.id.Phone);
        indirizzo=findViewById(R.id.Address);
        pass=findViewById(R.id.Password);
        Register=findViewById(R.id.cardView);
        Login=findViewById(R.id.textView2);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=id.getText().toString();
                String password= Methods.CifrarioCesare(pass.getText().toString(),3);
                RegisterInfo ri=new RegisterInfo(username,password,name.getText().toString(),lastname.getText().toString(),phone.getText().toString(),indirizzo.getText().toString());
                Register r=new Register("192.168.1.9",5000,ri);
                Methods.SJ(r);
                if(r.getExists()==null) {
                    new MessageDialog("Errore!","Username già utilizzato da altri utenti!").show(getSupportFragmentManager(),"dialog");
                }
                else {
                    LoginActivity.userid.setText(username);
                    onBackPressed();
                }
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}