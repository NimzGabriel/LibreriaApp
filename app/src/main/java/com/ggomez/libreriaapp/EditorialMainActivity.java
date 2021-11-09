package com.ggomez.libreriaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ggomez.libreriaapp.adapters.AutorAdapter;
import com.ggomez.libreriaapp.adapters.EditorialAdapter;
import com.ggomez.libreriaapp.models.Autor;
import com.ggomez.libreriaapp.models.Editorial;
import com.ggomez.libreriaapp.sqlite.DBAutor;
import com.ggomez.libreriaapp.sqlite.DBEditorial;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EditorialMainActivity extends AppCompatActivity {
    RecyclerView recyclerEditorial;
    FloatingActionButton agregarEditorialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editorial_main);

        recyclerEditorial = findViewById(R.id.recyclerEditorial);
        agregarEditorialButton = findViewById(R.id.agregarEditorialButton);
        agregarEditorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditorialMainActivity.this, RegistrarEditorialActivity.class));
            }
        });

        recyclerEditorial.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DBEditorial dbEditorial = new DBEditorial(getApplicationContext());
        ArrayList<Editorial> editoriales = dbEditorial.obtenerEditoriales();

        if(editoriales != null) {
            EditorialAdapter editorialAdapter = new EditorialAdapter(editoriales);
            recyclerEditorial.setAdapter(editorialAdapter);
        }
    }

}