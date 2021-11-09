package com.ggomez.libreriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ggomez.libreriaapp.models.Autor;
import com.ggomez.libreriaapp.models.Editorial;
import com.ggomez.libreriaapp.models.Estante;
import com.ggomez.libreriaapp.models.Libro;
import com.ggomez.libreriaapp.sqlite.DBAutor;
import com.ggomez.libreriaapp.sqlite.DBEditorial;
import com.ggomez.libreriaapp.sqlite.DBEstante;
import com.ggomez.libreriaapp.sqlite.DBLibro;

import java.util.ArrayList;

public class ModificarLibroActivity extends AppCompatActivity {
    EditText editTextModTituloLibro, editTextModDescripcionLibro,
            editTextModFechaLibro, editTextModCopiasLibro, editTextModPaginasLibro;
    Spinner spinnerModAutor, spinnerModEditorial, spinnerModEstante;
    Button buttonModLibro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_libro);

        editTextModTituloLibro = findViewById(R.id.editTextModTituloLibro);
        editTextModDescripcionLibro = findViewById(R.id.editTextModDescripcionLibro);
        editTextModFechaLibro = findViewById(R.id.editTextModFechaLibro);
        editTextModCopiasLibro = findViewById(R.id.editTextModCopiasLibro);
        editTextModPaginasLibro = findViewById(R.id.editTextModPaginasLibro);

        spinnerModAutor = findViewById(R.id.spinnerModAutor);
        spinnerModEditorial = findViewById(R.id.spinnerModEditorial);
        spinnerModEstante = findViewById(R.id.spinnerModEstante);

        cargarAutores();
        cargarEditoriales();
        cargarEstantes();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Libro libro = (Libro) bundle.get("libro");

        editTextModTituloLibro.setText(libro.getTitulo());
        editTextModDescripcionLibro.setText(libro.getDescripcion());
        editTextModFechaLibro.setText(libro.getFecha());
        editTextModCopiasLibro.setText(String.valueOf(libro.getCopias()));
        editTextModPaginasLibro.setText(String.valueOf(libro.getPaginas()));

        buttonModLibro = findViewById(R.id.buttonModLibro);
        buttonModLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = editTextModTituloLibro.getText().toString();
                String descripcion = editTextModDescripcionLibro.getText().toString();
                String fecha = editTextModFechaLibro.getText().toString();
                int copias = Integer.parseInt(editTextModCopiasLibro.getText().toString());
                int paginas = Integer.parseInt(editTextModPaginasLibro.getText().toString());

                Autor nuevo_autor = (Autor) spinnerModAutor.getSelectedItem();
                Editorial nueva_editorial = (Editorial) spinnerModEditorial.getSelectedItem();
                Estante nuevo_estante= (Estante) spinnerModEstante.getSelectedItem();

                DBLibro dbLibro = new DBLibro(getApplicationContext());
                int result = dbLibro.actualizarLibro(libro.getId_libro(), titulo, descripcion, fecha, copias, paginas, nuevo_autor, nueva_editorial, nuevo_estante);

                if(result > 0) {
                    Toast.makeText(getApplicationContext(), "Libro editado exitosamente", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

            }
        });
    }

    public void cargarAutores() {
        DBAutor dbAutor = new DBAutor(getApplicationContext());
        ArrayList<Autor> autores = dbAutor.obtenerAutores();

        if (autores != null) {
            ArrayAdapter<Autor> adapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    autores
            );
            spinnerModAutor.setAdapter(adapter);
        }
    }

    public void cargarEditoriales() {
        DBEditorial dbEditorial = new DBEditorial(getApplicationContext());
        ArrayList<Editorial> editoriales = dbEditorial.obtenerEditoriales();

        if (editoriales != null) {
            ArrayAdapter<Editorial> adapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    editoriales
            );
            spinnerModEditorial.setAdapter(adapter);
        }
    }

    public void cargarEstantes() {
        DBEstante dbEstante = new DBEstante(getApplicationContext());
        ArrayList<Estante> estantes = dbEstante.obtenerEstantes();

        if (estantes != null) {
            ArrayAdapter<Estante> adapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    estantes
            );
            spinnerModEstante.setAdapter(adapter);
        }
    }
}