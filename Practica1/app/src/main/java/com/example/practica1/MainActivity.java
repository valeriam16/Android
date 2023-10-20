package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override //Sobreescritura
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Manda llamar el metodo onCreate del papá (super) y le pasa el método del papá
        setContentView(R.layout.activity_main);
    }
}