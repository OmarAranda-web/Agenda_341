package com.tesji.agenda_341;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class HomeAgenda extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_agenda);
    }
    public void irAgregarContacto(View v){
        Intent i = new Intent(HomeAgenda.this, AgregarContacto.class);//Creamos una intancia a un Intenet el cual nos va a permitir en este caso
        //abrir la clase AgregarConcto
        startActivity(i);//Inciliamzamos el intent
    }
    public void salir(View v){
        finish();
    }
    public void buscar(View v){
        Intent i = new Intent(HomeAgenda.this, buscar.class);
        startActivity(i);
    }
    public  void vista(View v){
        Intent i = new Intent(HomeAgenda.this,VistaContacto.class);
        startActivity(i);
    }
}