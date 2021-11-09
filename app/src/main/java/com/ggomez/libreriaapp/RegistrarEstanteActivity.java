package com.ggomez.libreriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.libreriaapp.models.Estante;
import com.ggomez.libreriaapp.sqlite.DBEstante;

public class RegistrarEstanteActivity extends AppCompatActivity {
    EditText editTextLetraEstante, editTextNumEstante, editTextColorEstante;
    Button buttonRegistrarEstante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_estante);

        editTextLetraEstante = findViewById(R.id.editTextLetraEstante);
        editTextNumEstante = findViewById(R.id.editTextNumEstante);
        editTextColorEstante = findViewById(R.id.editTextColorEstante);
        buttonRegistrarEstante = findViewById(R.id.buttonRegistrarEstante);

        buttonRegistrarEstante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String letra = editTextLetraEstante.getText().toString();
                int numero = Integer.parseInt(editTextNumEstante.getText().toString());
                String color = editTextColorEstante.getText().toString();
                Estante estante = new Estante(letra, numero, color);

                DBEstante dbEstante = new DBEstante(getApplicationContext());
                long result = dbEstante.insertarEstante(estante);

                if(result > 0) {
                    Toast.makeText(getApplicationContext(), "Nuevo estante registrado exitosamente", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}