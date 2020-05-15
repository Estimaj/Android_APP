package com.example.pint_android_v3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {


    TextView Mail;
    TextView Password;
    TextInputLayout emailError;
    TextInputLayout passError;
    Button login;

    boolean isEmailValid, isPasswordValid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final basedados bd = new basedados(MainActivity.this);
        bd.apagar_Tabelas(bd.dbw);
        bd.criar_tabelas(bd.dbw);
        bd.InserirUtilizadoresParaTeste(bd.dbw);
        Mail = (EditText) findViewById(R.id.user_login_text_MainActivity);
        Password = (EditText) findViewById(R.id.user_password_text_MainActivity);
        emailError = (TextInputLayout)findViewById(R.id.User_BOX_MainActivity);
        passError = (TextInputLayout)findViewById(R.id.Pass_BOX_MainActivity);
        login = (Button) findViewById(R.id.Entrar_Motorista_MainActivity);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
            }
        });

    }

    private void Login_Start()
    {


        basedados bd = new basedados(MainActivity.this);
        if(bd.confirmarLogin(bd.dbr ,Mail.getText().toString(), Password.getText().toString())){
            Log.i("Sucesso:", "Existe uma conta com esse registo");
            Intent I = new Intent(MainActivity.this, desambiguacao.class);
            I.putExtra("User_Login", Mail.getText().toString());
            I.putExtra("Password_Login", Password.getText().toString());
            startActivity(I);
        }
        else{
            Log.i("Errado:", "Não existe uma conta com esse registo");
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(MainActivity.this, "Email ou Password Incorreta", duration);
            toast.show();}


    }

    public void SetValidation(){
        Log.i("Sucess:", "Entered SetValidation");
        // Check for a valid email address.
        if (Mail.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
            Log.i("Empty:", "No username input");
        } else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }

        // Check for a valid password.
        if (Password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
            Log.i("Empty:", "No password input");
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if(isEmailValid && isPasswordValid)
        {
            Login_Start();

        }
    }
}
