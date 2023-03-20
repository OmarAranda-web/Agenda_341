package com.tesji.agenda_341;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VistaContacto extends AppCompatActivity {
    //Declaramos una variable de tipo listView
    private ListView listaConcatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_contacto);
        listaConcatos = findViewById(R.id.listcontact);
        //Conectamos al servidor de SQLite
        ConexioSQLite conexioSQLite = new ConexioSQLite(this, "agenda", null, 1);//El cual con dicha intancia realizamos al conexion con la clase que contendra la base de datos,
        //el cual deberemos mandar de parametro la clase en oonde estamos, el nombre de la tabla, el cursor en este caso null y la version en este caso 1

        //Creamos otro objeto para la manipulacion de la base de datos.
        SQLiteDatabase baseDatos = conexioSQLite.getWritableDatabase(); //para poder manipular datos deberemos hacer una intancia a la clase SQLiteDataBase le daremos un nombre, y
        //Y en este caso debremos mandar el objeto de la instancia a a la clase ConexionSQLite y como se va ser un alta en registros se usara getWritableDatabase()


        //Declaramos una variable de tipo cursor, el cual extraemos los datos de la base agenda y las guardamos en una variable de tipo cursor
        Cursor datosUsuario = baseDatos.rawQuery("SELECT idUsuario || '.-\nNombre: ' || nomUsuario || '\nTelefono: ' || telefonoUsu || '\nCorrero: ' || correoUsu || '\nDomicilio: ' || domicilioUsu FROM agenda", null);
        //Contabilizamos el numero de registros en datosUsuario
        int cont= datosUsuario.getCount();//Contablizara el numero de registos de la base de datos y dicha cantidad se va a guardar en cont

        //Declaramos un array con el tamaño de los registros
        String arrayUsuario [] = new String[cont];

        //Movemos el registro del cursor al registro 1
        datosUsuario.moveToFirst();
        int i=0;
        //Pasamos los registros del cursor(datosUsuario) al array (arrayUsuario);
        while (i < cont){
            //Pasando  del cursor al array
            arrayUsuario[i]=datosUsuario.getString(0);//Extraemos el valor de la columna 0 y se almacenara en la posicion del arreglo
            datosUsuario.moveToNext();//El siguiente registro pasara a la sigueinte posicion
            i++;
        }
        //Pasando los datos del array al ArrayAdapter
        //cremaos el adaptador
        ArrayAdapter<String> adapterUsuarios = new ArrayAdapter<String>(
                //Pasamos los argumentos.
                this,
                android.R.layout.simple_list_item_1, arrayUsuario
        );

        //Pasamos el Array Adapter al list view
        listaConcatos.setAdapter(adapterUsuarios);
        baseDatos.close();
    }
    public void regresarHome3(View v){
        finish();
    }
}