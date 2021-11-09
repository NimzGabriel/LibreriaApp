package com.ggomez.libreriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.libreriaapp.models.Editorial;
import com.ggomez.libreriaapp.sqlite.DBEditorial;

public class RegistrarEditorialActivity extends AppCompatActivity {
    EditText editTextNombreEditorial, editTextNacionalidadEditorial;
    Button buttonRegistrarEditorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_editorial);

        editTextNombreEditorial = findViewById(R.id.editTextNombreEditorial);
        editTextNacionalidadEditorial = findViewById(R.id.editTextNacionalidadEditorial);
        buttonRegistrarEditorial = findViewById(R.id.buttonRegistrarEditorial);

        buttonRegistrarEditorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextNombreEditorial.getText().toString();
                String nacionalidad = editTextNacionalidadEditorial.getText().toString();
                Editorial editorial = new Editorial(nombre, nacionalidad);

                DBEditorial dbEditorial = new DBEditorial(getApplicationContext());
                long result = dbEditorial.insertarEditorial(editorial);

                if(result > 0) {
                    Toast.makeText(getApplicationContext(), "Nueva editorial registrada exitosamente", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}