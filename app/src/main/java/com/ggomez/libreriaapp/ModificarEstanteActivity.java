package com.ggomez.libreriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.libreriaapp.models.Estante;
import com.ggomez.libreriaapp.sqlite.DBEstante;

public class ModificarEstanteActivity extends AppCompatActivity {
    EditText editTextModLetraEstante, editTextModNumEstante, editTextModColorEstante;
    Button buttonModEstante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_estante);

        editTextModLetraEstante = findViewById(R.id.editTextModLetraEstante);
        editTextModNumEstante = findViewById(R.id.editTextModNumEstante);
        editTextModColorEstante = findViewById(R.id.editTextModColorEstante);
        buttonModEstante = findViewById(R.id.buttonModEstante);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Estante estante = (Estante) bundle.get("estante");

        editTextModLetraEstante.setText(estante.getLetra());
        editTextModNumEstante.setText(String.valueOf(estante.getNumero()));
        editTextModColorEstante.setText(estante.getColor());

        buttonModEstante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String letra = editTextModLetraEstante.getText().toString();
                int numero = Integer.parseInt(editTextModNumEstante.getText().toString());
                String color = editTextModColorEstante.getText().toString();

                DBEstante dbEstante = new DBEstante(getApplicationContext());
                int result = dbEstante.actualizarEditorial(estante.getId_estante(), letra, numero, color);

                if(result > 0) {
                    Toast.makeText(getApplicationContext(), "Estante editado exitosamente", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), EstanteMainActivity.class));
                    finish();
                }

            }
        });

    }
}