package com.ggomez.libreriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.libreriaapp.models.Autor;
import com.ggomez.libreriaapp.sqlite.DBAutor;

public class RegistrarAutorActivity extends AppCompatActivity {
    EditText editTextNombreAutor, editTextApellidosAutor, editTextNacionalidadAutor;
    Button buttonRegistrarAutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_autor);

        editTextNombreAutor = findViewById(R.id.editTextNombreAutor);
        editTextApellidosAutor = findViewById(R.id.editTextApellidosAutor);
        editTextNacionalidadAutor = findViewById(R.id.editTextNacionalidadAutor);

        buttonRegistrarAutor = findViewById(R.id.buttonRegistrarAutor);
        buttonRegistrarAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextNombreAutor.getText().toString();
                String apellidos = editTextApellidosAutor.getText().toString();
                String nacionalidad = editTextNacionalidadAutor.getText().toString();
                Autor autor = new Autor(nombre, apellidos, nacionalidad);

                DBAutor dbAutor = new DBAutor(getApplicationContext());
                long result = dbAutor.insertarAutor(autor);
                if(result > 0) {
                    Toast.makeText(getApplicationContext(), "Nuevo autor registrado exitosamente", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}