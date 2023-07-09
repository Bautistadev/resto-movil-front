package com.example.resto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.resto.EntityDTO.LoginDTO;
import com.example.resto.EntityDTO.TokenObject;
import com.example.resto.Service.LoginService;
import com.example.resto.Utils.Apis;

import java.io.IOError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements Callback<TokenObject>, View.OnClickListener{

    private Button btnLogin;
    private TextView txtUserName;
    private TextView txtPassword;
    private Intent panelEmpleadoMenuPrincipal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.btnLogin = findViewById(R.id.btnLogin);
        this.txtUserName = findViewById(R.id.txtUserName);
        this.txtPassword = findViewById(R.id.txtPassword);

        this.panelEmpleadoMenuPrincipal =  new Intent(Login.this,EmpleadoMenuPrincipal.class);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        LoginDTO login = new LoginDTO(this.txtUserName.getText().toString(),this.txtPassword.getText().toString());
        System.out.println(login.toString());
        Call<TokenObject> call = Apis.getLoginService().validateSeccion(login);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<TokenObject> call, Response<TokenObject> response) {
        if (response.isSuccessful()) {
            TokenObject token = response.body();

            //GUARDAMOS EL TOKEN EN MEMORIA
            SharedPreferences memoria = getSharedPreferences("Login",MODE_PRIVATE);
            SharedPreferences.Editor editor = memoria.edit();
            editor.putString("token",token.getAccess_token());

            //NOS MOVEMOS DE ACTIVITY
            startActivity(this.panelEmpleadoMenuPrincipal);

        } else {
            if(response.errorBody() != null){
                try {
                    System.out.println("error");
                }catch (IOError e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onFailure(Call<TokenObject> call, Throwable t) {
        Log.e("MainActivity", "Error en la consulta a la API", t);
    }
}