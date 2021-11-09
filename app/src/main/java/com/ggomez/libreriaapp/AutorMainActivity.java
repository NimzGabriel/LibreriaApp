package com.ggomez.libreriaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ggomez.libreriaapp.adapters.AutorAdapter;
import com.ggomez.libreriaapp.models.Autor;
import com.ggomez.libreriaapp.sqlite.DBAutor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AutorMainActivity extends AppCompatActivity {
    RecyclerView recyclerAutor;
    FloatingActionButton agregarAutorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor_main);

        recyclerAutor = findViewById(R.id.recyclerAutor);
        agregarAutorButton = findViewById(R.id.agregarAutorButton);
        agregarAutorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AutorMainActivity.this, RegistrarAutorActivity.class));
            }
        });

        recyclerAutor.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DBAutor dbAutor = new DBAutor(getApplicationContext());
        ArrayList<Autor> autores = dbAutor.obtenerAutores();

        if(autores != null) {
            AutorAdapter autorAdapter = new AutorAdapter(autores);
            recyclerAutor.setAdapter(autorAdapter);
        }
    }

}