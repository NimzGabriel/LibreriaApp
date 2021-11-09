package com.ggomez.libreriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.libreriaapp.models.Editorial;
import com.ggomez.libreriaapp.sqlite.DBEditorial;

public class ModificarEditorialActivity extends AppCompatActivity {
    EditText editTextModNombreEditorial, editTextModNacionalidadEditorial;
    Button buttonModEditorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_editorial);

        editTextModNombreEditorial = findViewById(R.id.editTextModNombreEditorial);
        editTextModNacionalidadEditorial = findViewById(R.id.editTextModNacionalidadEditorial);
        buttonModEditorial = findViewById(R.id.buttonModEditorial);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Editorial editorial = (Editorial) bundle.get("editorial");

        editTextModNombreEditorial.setText(editorial.getNombre());
        editTextModNacionalidadEditorial.setText(editorial.getNacionalidad());

        buttonModEditorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextModNombreEditorial.getText().toString();
                String nacionalidad = editTextModNacionalidadEditorial.getText().toString();

                DBEditorial dbEditorial = new DBEditorial(getApplicationContext());
                int result = dbEditorial.actualizarEditorial(editorial.getId_editorial(), nombre, nacionalidad);

                if(result > 0) {
                    Toast.makeText(getApplicationContext(), "Editorial editada exitosamente", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), EditorialMainActivity.class));
                    finish();
                }

            }
        });


    }
}