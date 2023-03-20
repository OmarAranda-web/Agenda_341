package com.tesji.agenda_341;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexioSQLite extends SQLiteOpenHelper {//Para crear la conexion deberemos heredar de la Clase SQLiteOpenHelper lo cual deberemos de implementar los dos metodos
//Tambien se implementara el constructor.
    public ConexioSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
//Implementamos los dos metodos onCreate y onUpdate el cual ambos van a estar sobrecargados
    @Override
    public void onCreate(SQLiteDatabase db) {//Se utilizara el parametro db para crear la tabla de la base de datos
        //Y con esta linea de codigo quedaria creeada nuestra tabla de la base de datos.
        db.execSQL("CREATE TABLE agenda(idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, nomUsuario TEXT, telefonoUsu TEXT, correoUsu TEXT,domicilioUsu TEXT)");
    }
    //Este metodo se ejecutara cuando la version de la base de datos cambie
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
