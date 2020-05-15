package com.example.pint_android_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class desambiguacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("oncreate:", "on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desambiguacao);
        TextView Mail = (EditText) findViewById(R.id.user_login_text);
        TextView Password = (EditText) findViewById(R.id.user_password_text);

        Intent I= getIntent();
        Bundle b = I.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("User_Login");
            Mail.setText(j);
            j = (String) b.get("Password_Login");
            Password.setText(j);
        }
    }
}
