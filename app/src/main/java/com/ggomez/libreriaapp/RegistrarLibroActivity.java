package com.ggomez.libreriaapp;

import androidx.appcompat.app.AppCompatActivity;

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

public class RegistrarLibroActivity extends AppCompatActivity {
    EditText editTextTituloLibro, editTextDescripcionLibro,
            editTextFechaLibro, editTextCopiasLibro, editTextPaginasLibro;
    Spinner spinnerAutor, spinnerEditorial, spinnerEstante;
    Button buttonRegistrarLibro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_libro);

        editTextTituloLibro = findViewById(R.id.editTextTituloLibro);
        editTextDescripcionLibro = findViewById(R.id.editTextDescripcionLibro);
        editTextFechaLibro = findViewById(R.id.editTextFechaLibro);
        editTextCopiasLibro = findViewById(R.id.editTextCopiasLibro);
        editTextPaginasLibro = findViewById(R.id.editTextPaginasLibro);

        spinnerAutor = findViewById(R.id.spinnerAutor);
        spinnerEditorial = findViewById(R.id.spinnerEditorial);
        spinnerEstante = findViewById(R.id.spinnerEstante);

        cargarAutores();
        cargarEditoriales();
        cargarEstantes();

        buttonRegistrarLibro = findViewById(R.id.buttonRegistrarLibro);
        buttonRegistrarLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = editTextTituloLibro.getText().toString();
                String descripcion = editTextDescripcionLibro.getText().toString();
                String fecha = editTextFechaLibro.getText().toString();
                int copias = Integer.parseInt(editTextCopiasLibro.getText().toString());
                int paginas = Integer.parseInt(editTextPaginasLibro.getText().toString());

                Autor autor = (Autor) spinnerAutor.getSelectedItem();
                int id_autor = autor.getId_autor();
                Editorial editorial = (Editorial) spinnerEditorial.getSelectedItem();
                int id_editorial = editorial.getId_editorial();
                Estante estante = (Estante) spinnerEstante.getSelectedItem();
                int id_estante = estante.getId_estante();

                DBLibro dbLibro = new DBLibro(getApplicationContext());
                long result = dbLibro.insertarLibro(titulo, descripcion, fecha, copias, paginas, id_autor, id_editorial, id_estante);

                if(result > 0) {
                    Toast.makeText(getApplicationContext(), "Nuevo libro registrado exitosamente", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void cargarAutores() {
        DBAutor dbAutor = new DBAutor(getApplicationContext());
        ArrayList<Autor> autores = dbAutor.obtenerAutores();

        if(autores != null) {
            ArrayAdapter<Autor> adapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    autores
            );
            spinnerAutor.setAdapter(adapter);
        }
    }

    public void cargarEditoriales() {
        DBEditorial dbEditorial = new DBEditorial(getApplicationContext());
        ArrayList<Editorial> editoriales = dbEditorial.obtenerEditoriales();

        if(editoriales != null) {
            ArrayAdapter<Editorial> adapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    editoriales
            );
            spinnerEditorial.setAdapter(adapter);
        }
    }

    public void cargarEstantes() {
        DBEstante dbEstante = new DBEstante(getApplicationContext());
        ArrayList<Estante> estantes = dbEstante.obtenerEstantes();

        if(estantes != null) {
            ArrayAdapter<Estante> adapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    estantes
            );
            spinnerEstante.setAdapter(adapter);
        }
    }


}