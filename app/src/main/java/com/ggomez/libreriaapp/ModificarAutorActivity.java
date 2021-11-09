package com.ggomez.libreriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.libreriaapp.models.Autor;
import com.ggomez.libreriaapp.sqlite.DBAutor;

public class ModificarAutorActivity extends AppCompatActivity {
    EditText editTextModNombreAutor, editTextModApellidosAutor, editTextModNacionalidadAutor;
    Button buttonModAutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_autor);

        editTextModNombreAutor = findViewById(R.id.editTextModNombreAutor);
        editTextModApellidosAutor = findViewById(R.id.editTextModApellidosAutor);
        editTextModNacionalidadAutor = findViewById(R.id.editTextModNacionalidadAutor);
        buttonModAutor = findViewById(R.id.buttonModAutor);

        // GET INTENT
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Autor autor = (Autor) bundle.get("autor");

        // SET DATOS
        editTextModNombreAutor.setText(autor.getNombre());
        editTextModApellidosAutor.setText(autor.getApellidos());
        editTextModNacionalidadAutor.setText(autor.getNacionalidad());

        buttonModAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextModNombreAutor.getText().toString();
                String apellidos = editTextModApellidosAutor.getText().toString();
                String nacionalidad = editTextModNacionalidadAutor.getText().toString();
                DBAutor dbAutor = new DBAutor(getApplicationContext());
                int result = dbAutor.actualizarAutor(autor.getId_autor(), nombre, apellidos, nacionalidad);

                if(result > 0) {
                    Toast.makeText(getApplicationContext(), "Autor editado exitosamente", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), AutorMainActivity.class));
                    finish();
                }

            }
        });

    }
}