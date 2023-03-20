package com.tesji.agenda_341;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creando el Tread (Hilo)
        //Handler controla el tiempo.
        new Handler().postDelayed(new Runnable() {//Controla el Hilo para abrir la siguiente pagina
            @Override
            public void run() {
                Intent abrirHome = new Intent(MainActivity.this,HomeAgenda.class);//El intent esta sobrecargado entonces hay que decirle que actividad se debe realizar
                //El cual debemos de mencionar en donde esta y el destino al que va a mandar a llamar
                startActivity(abrirHome);//Iniciamos la actividad
                finish();//Termina la actividad actual.
            }
        },5000);//Tiempo que va a tadar la primera pantalla abierta
    }
}