package com.tesji.agenda_341;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class buscar extends AppCompatActivity {
    //Declaramos la instancia a la clase ConexioSQLite al principio de esta ya que la ocuparemos en varios metodos de la clase buscar
    ConexioSQLite conexioSQLite = new ConexioSQLite(this, "agenda", null, 1);//El cual con dicha intancia realizamos al conexion con la clase que contendra la base de datos,
    //el cual deberemos mandar de parametro la clase deonde estamos, el nombre de la tabla, el cursor en este caso ull y la version en tees caso 1
     private EditText txedidUsu;
     private EditText edtxNombre;
     private EditText edtxTelefono;
     private EditText edtxCorreo;
     private EditText edtxDomicilio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        txedidUsu=findViewById(R.id.txtidUsuario);
        edtxNombre = findViewById(R.id.txtedNombre);
        edtxTelefono = findViewById(R.id.txtedTelefono);
        edtxCorreo = findViewById(R.id.txtedCorreo);
        edtxDomicilio = findViewById(R.id.txtedDomicilio);
    }
    public void buscar(View v){
        //Creamos otro objeto para la manipulacion de la base de datos.
        SQLiteDatabase baseDatos = conexioSQLite.getWritableDatabase(); //para poder manipular datos deberemos hacer una intancia a la clase SQLiteDataBase le daremos un nombre, y
        //Y en este caso debremos mandar el objeto de la instancia a a la clase ConexionSQLite y como se va ser un alta en registros se usara getWritableDatabase()

        //Extraer el id del Usuario.
        String id = txedidUsu.getText().toString();
        if(id.trim().length() == 0){
            txedidUsu.setError("Debe regresar el ID del Usuario");
            return ;
        }
        //Extraemos el registo de la tabla Agenda
        Cursor resgistro = baseDatos.rawQuery("SELECT * FROM agenda WHERE idUsuario= "+ id,null);//El cual vamos a seleccionar todos los campos
        //de la tabla agenda donde idUsuario ya que este campo es unico y no se puede duplicar por lo que para seleccianar el registro debe ser igual a id

        if(resgistro.moveToFirst()){//el cual si se encontro el registro lo movera al primer registro sino mandara un false
            //El cual una vez que se cumpla la condicion se enviara el texto que rescatara de cada campo de la tabla agenda.
            edtxNombre.setText(resgistro.getString(1));
            edtxTelefono.setText(resgistro.getString(2));
            edtxCorreo.setText(resgistro.getString(3));
            edtxDomicilio.setText(resgistro.getString(4));
        }else{
            Toast.makeText(this, "Usuario no encontrado con id ="+ id, Toast.LENGTH_SHORT).show();
            limpiarformulario();
        }
        baseDatos.close();
    }
    private void limpiarformulario(){
        edtxNombre.setText(null);
        edtxTelefono.setText((null));
        edtxCorreo.setText(null);
        edtxDomicilio.setText(null);
        txedidUsu.requestFocus();
    }

    public void actualizar(View v){
        //Creamos otro objeto para la manipulacion de la base de datos.
        SQLiteDatabase baseDatos = conexioSQLite.getWritableDatabase(); //para poder manipular datos deberemos hacer una intancia a la clase SQLiteDataBase le daremos un nombre, y
        //Y en este caso debremos mandar el objeto de la instancia a a la clase ConexionSQLite y como se va ser un alta en registros se usara getWritableDatabase()

        //Extraemos los datos del formulario ya editado.
        String id = txedidUsu.getText().toString();
        String nombre = edtxNombre.getText().toString();
        String telefono = edtxTelefono.getText().toString();
        String correo = edtxCorreo.getText().toString();
        String domici = edtxDomicilio.getText().toString();

        //Procuramos que los campos estenllenos para evitar tener registros vacios
        if(id.trim().length() == 0){
            txedidUsu.setError("El id del Usuario no puede estar vacio");
            return ;
        }
        if(nombre.trim().length() == 0){
            edtxNombre.setError("El nombre del Usuario no puede estar vacio");
            return ;
        }
        if(telefono.trim().length() == 0){
            edtxTelefono.setError("El Telefono del Usuario no puede estar vacio");
            return ;
        }
        if(correo.trim().length() == 0){
            edtxCorreo.setError("El Correo del Usuario no puede estar vacio");
            return ;
        }
        if(domici.trim().length() == 0){
            edtxDomicilio.setError("El Domicilio del Usuario no puede estar vacio");
            return ;
        }

        //Creamos el objeto ContentValues con los nuevos  valores del registro
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("nomUsuario", nombre);
        nuevoRegistro.put("telefonoUsu", telefono);
        nuevoRegistro.put("correoUsu",correo);
        nuevoRegistro.put("domicilioUsu",domici);

        //Actualizamos el registro agenda
        //El cual hara la actualizacion de los datos el cual le diremos a que tabla la hara, el contentValues de donde tomara los respectivos valores
        //a actualizar, despues indicaremos que registro sera por medio del campo id, y no llevara argumentos
        //Ademas alacenaremos el numero de campos actualizados en la variable numerodeRegistros
        int numerodeRegistrosAct = baseDatos.update("agenda",nuevoRegistro,"idUsuario =" + id,null);
        if(numerodeRegistrosAct == 1){
            Toast.makeText(this,"Agenda Actualizada correctamente",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Problemas al actualizar el Usuario",Toast.LENGTH_SHORT).show();
        }
        baseDatos.close();
    }

    public void borrar(View v){
        //Creamos otro objeto para la manipulacion de la base de datos.
        SQLiteDatabase baseDatos = conexioSQLite.getWritableDatabase(); //para poder manipular datos deberemos hacer una intancia a la clase SQLiteDataBase le daremos un nombre, y
        //Y en este caso debremos mandar el objeto de la instancia a a la clase ConexionSQLite y como se va ser un alta en registros se usara getWritableDatabase()

        //Extraemos el id que se escribio.
        String id = txedidUsu.getText().toString();
        if(id.trim().length() == 0){
            txedidUsu.setError("El id del Usuario no puede estar vacio");
            return ;
        }
        //Borramos el objeto de la base de datos
        int registrosborrados = baseDatos.delete("agenda","idUsuario =" + id,null);
        if(registrosborrados == 1){
            Toast.makeText(this,"Se elimino el registor con el id =" + id,Toast.LENGTH_SHORT).show();
            txedidUsu.setText(null);
            limpiarformulario();
        }
        baseDatos.close();
    }
    public void cancelar2(View v){
        txedidUsu.setText(null);
        limpiarformulario();
    }

    public void regresarHome2(View v){
        finish();
    }

}