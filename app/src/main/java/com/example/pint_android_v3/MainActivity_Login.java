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

import com.example.pint_android_v3.menus.menu_municipe;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity_Login extends AppCompatActivity {

    private Retrofit retrofit;
    private BaseDadosInterface baseDadosInterface;
    private String BASE_URL ="http://10.0.2.2:3000";

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
        Mail = findViewById(R.id.user_login_text_MainActivity);
        Password = findViewById(R.id.user_password_text_MainActivity);
        emailError = findViewById(R.id.User_BOX_MainActivity);
        passError = findViewById(R.id.Pass_BOX_MainActivity);
        login = findViewById(R.id.Entrar_Motorista_MainActivity);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        //Log.i("info_AQUI", "ainda n fiz login!!");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
            }
        });

    }

    private void Login_Start()
    {
        HashMap<String, String> map = new HashMap<>();

        map.put("email", Mail.getText().toString());
        map.put("password", Password.getText().toString());

        Call<Post> call = baseDadosInterface.executeLogin(map);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                //response vai ser um obj do tipo Post logo contem os campos do Post
                if(!response.isSuccessful()){
                    makeToastForMain("Credenciais Erradas");
                    return;
                }
                if(response.code() == 200) {
                    //Log.i("Server Info log in:", ""+ response.body().toString());
                    int tipo = response.body().getTipo();
                    switch (tipo) {
                        case 5:{ //cidadao
                            Intent I_c = new Intent(MainActivity_Login.this, menu_municipe.class);
                            I_c.putExtra("user_id", response.body().getId());
                            startActivity(I_c);
                            break;
                        }
                        case 6:{ //motorista
                            Intent I = new Intent(MainActivity_Login.this, desambiguacao.class);
                            I.putExtra("User_Login", Mail.getText().toString());
                            I.putExtra("Password_Login", Password.getText().toString());
                            I.putExtra("user_id", response.body().getId());
                            startActivity(I);
                            break;
                        }
                        default:{
                            makeToastForMain("Erro no tipo de utilizador, registo mal efetuado!");
                            Log.i("Tipo_Utilizador", ": " + tipo);
                        }

                    }

                }
                else if(response.code() == 404){
                    makeToastForMain("Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity_Login.this, "onFailure ln97: "+ t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

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

    public void makeToastForMain(String msg){
        Toast.makeText(MainActivity_Login.this, msg,
                Toast.LENGTH_LONG).show();
    }
}
