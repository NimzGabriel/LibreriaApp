package com.ggomez.libreriaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ggomez.libreriaapp.adapters.EditorialAdapter;
import com.ggomez.libreriaapp.adapters.EstanteAdapter;
import com.ggomez.libreriaapp.models.Editorial;
import com.ggomez.libreriaapp.models.Estante;
import com.ggomez.libreriaapp.sqlite.DBEditorial;
import com.ggomez.libreriaapp.sqlite.DBEstante;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EstanteMainActivity extends AppCompatActivity {
    RecyclerView recyclerEstantes;
    FloatingActionButton agregarEstanteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estante_main);

        recyclerEstantes = findViewById(R.id.recyclerEstantes);
        agregarEstanteButton = findViewById(R.id.agregarEstanteButton);
        agregarEstanteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EstanteMainActivity.this, RegistrarEstanteActivity.class));
            }
        });

        recyclerEstantes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DBEstante dbEstante = new DBEstante(getApplicationContext());
        ArrayList<Estante> estantes = dbEstante.obtenerEstantes();

        if(estantes != null) {
            EstanteAdapter estanteAdapter = new EstanteAdapter(estantes);
            recyclerEstantes.setAdapter(estanteAdapter);
        }
    }
}