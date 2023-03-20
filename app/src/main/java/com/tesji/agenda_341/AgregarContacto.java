package com.tesji.agenda_341;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AgregarContacto extends AppCompatActivity {
    //Decleramos cada una de las variables que haran referencia al objeto del activity agregar contacto
    private TextInputEditText txNombre;
    private TextInputEditText txTelefono;
    private TextInputEditText txCorreo;
    private TextInputEditText txDomicilio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);

        txNombre = findViewById(R.id.textNombre);
        txTelefono = findViewById(R.id.textTelefono);
        txCorreo = findViewById(R.id.textCorreo);
        txDomicilio = findViewById(R.id.textDomicilio);
    }

    public void agregarUsuario(View v){
        //Declaramos una 4 variables de tipo String que guardaran los datos que se obtengan de los TextInputLayout, el cual los va a transformar en toString
        String nombre = txNombre.getText().toString();
        String tel = txTelefono.getText().toString();
        String correo = txCorreo.getText().toString();
        String domicilio = txDomicilio.getText().toString();
        //Validando que los datos de las cajas de texto no esten vacios, para no alamcenar espacios vacios en la vase de datos
        if(nombre.trim().length() == 0){
            txNombre.setError("El campo nombre no debe estar Vacio");
            return ;//En caso de que un if se ejecute el return hara que las de abajo no se ejecuten mas
        }
        if(tel.trim().length() == 0){
            txTelefono.setError("El campo telefono no debe estar Vacio");
            return ;
        }
        if(correo.trim().length() == 0){
            txCorreo.setError("El campo Correo no debe estar Vacio");
            return ;
        }
        if(domicilio.trim().length() == 0){
            txDomicilio.setError("El campo Domicilio no debe estar Vacio");
            return ;
        }
        //Las instrucciones se ejecutaran si los campos no estan vacios
        //Conectamos al servidor de SQLite
        ConexioSQLite conexioSQLite = new ConexioSQLite(this, "agenda", null, 1);//El cual con dicha intancia realizamos al conexion con la clase que contendra la base de datos,
        //el cual deberemos mandar de parametro la clase deonde estamos, el nombre de la tabla, el cursor en este caso ull y la version en tees caso 1

        //Creamos otro objeto para la manipulacion de la base de datos.
        SQLiteDatabase baseDatos = conexioSQLite.getWritableDatabase(); //para poder manipular datos deberemos hacer una intancia a la clase SQLiteDataBase le daremos un nombre, y
        //Y en este caso debremos mandar el objeto de la instancia a a la clase ConexionSQLite y como se va ser un alta en registros se usara getWritableDatabase()

        //Crear un objeto para construir el registro del Usuario
        ContentValues registroUsuario = new ContentValues();
        //Pasaremos cada dato nombre, domicilio, etc. a la base de datos.
        registroUsuario.put("nomUsuario", nombre);
        //Primero nos va a pedir una llave el cual es el nombre del campo de la tabla, y el segundo parametro va ser la variable que contenga la informacion el cual se va registrar en el campo
        registroUsuario.put("telefonoUsu", tel);
        registroUsuario.put("correoUsu",correo);
        registroUsuario.put("domicilioUsu",domicilio);

        //En este caso guardaremos ya los valore a la tabla, el cual el primer parameto sera el nombre de la tabla en este caso se llama agenda, el segundo ser un objeto en este caso no le enviariamos
        // ninguno entonces se queda como nulo y el ultimo el la intancia que contendra las variables del registro.
        baseDatos.insert("agenda",null, registroUsuario);
        Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
        limpiar();
        conexioSQLite.close();//Nos servira para ahorrar espacion en memoria que cuando ya se realice el registro se cierre la base de datos
    }
    //Metodo que limpiara las cajas de texto una vez que se guarde los datos
    private void limpiar(){
        txNombre.setText(null);
        txTelefono.setText(null);
        txCorreo.setText(null);
        txDomicilio.setText(null);
        txNombre.requestFocus();//Coloca el puntero en la caja de texto
    }
    public void regresarHome(View v){
        finish();
    }
    public void cancelarTexto(View v){
        limpiar();
    }

}