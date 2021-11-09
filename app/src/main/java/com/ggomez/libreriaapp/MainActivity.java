package com.ggomez.libreriaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.ggomez.libreriaapp.adapters.LibroAdapter;
import com.ggomez.libreriaapp.models.Autor;
import com.ggomez.libreriaapp.models.Editorial;
import com.ggomez.libreriaapp.models.Libro;
import com.ggomez.libreriaapp.sqlite.DBAutor;
import com.ggomez.libreriaapp.sqlite.DBEditorial;
import com.ggomez.libreriaapp.sqlite.DBLibro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerLibros;
    FloatingActionButton agregarLibroButton;
    EditText editTextFiltroNombre;
    Spinner spinnerFiltroAutor, spinnerFiltroEditorial;
    Button buttonFiltrarLibro, buttonLimpiarFiltro;
    Switch switchTitulo, switchAutor, switchEditorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonFiltrarLibro = findViewById(R.id.buttonFiltrarLibro);
        recyclerLibros = findViewById(R.id.recyclerLibros);
        editTextFiltroNombre = findViewById(R.id.editTextFiltroNombre);
        switchTitulo = findViewById(R.id.switchTitulo);
        switchAutor = findViewById(R.id.switchAutor);
        switchEditorial = findViewById(R.id.switchEditorial);
        spinnerFiltroAutor = findViewById(R.id.spinnerFiltroAutor);
        spinnerFiltroEditorial = findViewById(R.id.spinnerFiltroEditorial);
        cargarLibros();

        editTextFiltroNombre.setEnabled(false);
        spinnerFiltroAutor.setEnabled(false);
        spinnerFiltroEditorial.setEnabled(false);

        // Switch titulo
        switchTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchTitulo.isChecked()) {
                    editTextFiltroNombre.setEnabled(true);
                    buttonFiltrarLibro.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!editTextFiltroNombre.equals("")) {
                                cargarLibrosPorNombre();
                            }
                        }
                    });

                } else {
                    editTextFiltroNombre.setEnabled(false);
                    editTextFiltroNombre.setText("");
                }
            }
        });

        // Switch autor
        switchAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchAutor.isChecked()) {
                    spinnerFiltroAutor.setEnabled(true);
                    cargarAutores();
                    buttonFiltrarLibro.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cargarLibrosPorAutor();
                        }
                    });
                } else {
                    spinnerFiltroAutor.setEnabled(false);
                    spinnerFiltroAutor.setSelection(0);
                    cargarLibros();
                }
            }
        });

        // Switch editorial
        switchEditorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchEditorial.isChecked()) {
                    spinnerFiltroEditorial.setEnabled(true);
                    cargarEditoriales();
                    buttonFiltrarLibro.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cargarLibrosPorEditorial();
                        }
                    });
                } else {
                    spinnerFiltroEditorial.setEnabled(false);
                    spinnerFiltroEditorial.setSelection(0);
                    cargarLibros();
                }
            }
        });

        // Agregar libro
        agregarLibroButton = findViewById(R.id.agregarLibroButton);
        agregarLibroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrarLibroActivity.class));
            }
        });

        // Limpiar
        buttonLimpiarFiltro = findViewById(R.id.buttonLimpiarFiltro);
        buttonLimpiarFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Carga todos los libros denuevo
                editTextFiltroNombre.setText("");
                spinnerFiltroAutor.setSelection(0);
                spinnerFiltroEditorial.setSelection(0);
                cargarLibros();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mi_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_autores:
                startActivity(new Intent(MainActivity.this, AutorMainActivity.class));
                return true;
            case R.id.item_editoriales:
                startActivity(new Intent(MainActivity.this, EditorialMainActivity.class));
                return true;
            case R.id.item_estantes:
                startActivity(new Intent(MainActivity.this, EstanteMainActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cargarPorAutorYEditorial() {
        Autor autor = (Autor) spinnerFiltroAutor.getSelectedItem();
        Editorial editorial = (Editorial) spinnerFiltroEditorial.getSelectedItem();
        recyclerLibros.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        DBLibro dbLibro = new DBLibro(getApplicationContext());
        ArrayList<Libro> AutoresYEditoriales = dbLibro.filtroAutorYEditorial(autor.getId_autor(), editorial.getId_editorial());

        if(AutoresYEditoriales != null) {
            LibroAdapter libroAdapter = new LibroAdapter(AutoresYEditoriales);
            recyclerLibros.setAdapter(libroAdapter);
        } else {
            Toast.makeText(getApplicationContext(), "No hay resultados con ese filtro", Toast.LENGTH_SHORT).show();
        }
    }

    public void cargarLibrosPorAutor() {
        Autor autor = (Autor) spinnerFiltroAutor.getSelectedItem();
        recyclerLibros.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        DBLibro dbLibro = new DBLibro(getApplicationContext());
        ArrayList<Libro> librosPorAutor = dbLibro.obtenerLibrosFiltroAutor(autor.getId_autor());

        if(librosPorAutor != null) {
            LibroAdapter libroAdapter = new LibroAdapter(librosPorAutor);
            recyclerLibros.setAdapter(libroAdapter);
        }
    }

    public void cargarLibrosPorEditorial() {
        Editorial editorial = (Editorial) spinnerFiltroEditorial.getSelectedItem();
        recyclerLibros.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        DBLibro dbLibro = new DBLibro(getApplicationContext());
        ArrayList<Libro> librosPorEditorial = dbLibro.obtenerLibrosFiltroEditorial(editorial.getId_editorial());

        if(librosPorEditorial != null) {
            LibroAdapter libroAdapter = new LibroAdapter(librosPorEditorial);
            recyclerLibros.setAdapter(libroAdapter);
        }
    }

    public void cargarLibrosPorNombre() {
        String nombre = editTextFiltroNombre.getText().toString();
        recyclerLibros.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        DBLibro dbLibro = new DBLibro(getApplicationContext());
        ArrayList<Libro> librosPorNombre = dbLibro.obtenerLibrosFiltroTitulo(nombre);

        if(librosPorNombre != null) {
            LibroAdapter libroPorNombreAdapter = new LibroAdapter(librosPorNombre);
            recyclerLibros.setAdapter(libroPorNombreAdapter);
        }
    }

    public void cargarLibros() {
        recyclerLibros.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DBLibro dbLibro = new DBLibro(getApplicationContext());
        ArrayList<Libro> libros = dbLibro.obtenerLibros();

        if(libros != null) {
            LibroAdapter libroAdapter = new LibroAdapter(libros);
            recyclerLibros.setAdapter(libroAdapter);
        }
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
            spinnerFiltroAutor.setAdapter(adapter);
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
            spinnerFiltroEditorial.setAdapter(adapter);
        }
    }
}