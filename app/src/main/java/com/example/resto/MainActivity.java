package com.example.resto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnEmpleados;
    private Button btnPizzeria;
    private Intent panelLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnPizzeria = findViewById(R.id.btnPizzeria);
        this.btnEmpleados =  findViewById(R.id.btnEmpleados);

        this.panelLogin = new Intent(MainActivity.this, Login.class);

        this.btnEmpleados.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnEmpleados){
            startActivity(this.panelLogin);
        }
    }

    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link Ordenes#newInstance} factory method to
     * create an instance of this fragment.
     */

}