package com.ggomez.libreriaapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ggomez.libreriaapp.AutorMainActivity;
import com.ggomez.libreriaapp.EditorialMainActivity;
import com.ggomez.libreriaapp.EstanteMainActivity;
import com.ggomez.libreriaapp.ModificarAutorActivity;
import com.ggomez.libreriaapp.ModificarEstanteActivity;
import com.ggomez.libreriaapp.R;
import com.ggomez.libreriaapp.models.Autor;
import com.ggomez.libreriaapp.models.Editorial;
import com.ggomez.libreriaapp.models.Estante;
import com.ggomez.libreriaapp.sqlite.DBEditorial;
import com.ggomez.libreriaapp.sqlite.DBEstante;

import java.util.ArrayList;

public class EstanteAdapter extends RecyclerView.Adapter<EstanteAdapter.ViewHolder> {
    ArrayList<Estante> estantes;

    public EstanteAdapter(ArrayList<Estante> estantes) {
        this.estantes = estantes;
    }

    @NonNull
    @Override
    public EstanteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estante, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EstanteAdapter.ViewHolder holder, int position) {
        holder.cargarEstantes(estantes.get(position));
        Context context = holder.itemView.getContext();

        // Modificar
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Estante estante = estantes.get(position);
                Intent intent = new Intent(context, ModificarEstanteActivity.class);
                intent.putExtra("estante", estante);
                context.startActivity(intent);
                ((EstanteMainActivity)context).finish();
            }
        });


        // Eliminar
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setTitle("Eliminar Estante");
                alerta.setMessage("¿Seguro que desea eliminar el estante?");
                alerta.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Estante estante = estantes.get(position);
                        DBEstante dbEstante = new DBEstante(context);
                        int result = dbEstante.eliminarEstante(estante.getId_estante());

                        if(result > 0) {
                            Toast.makeText(context, "Estante eliminado exitosamente", Toast.LENGTH_LONG).show();
                            context.startActivity(new Intent(context, EstanteMainActivity.class));
                            ((EstanteMainActivity)context).finish();
                        }
                    }
                });

                alerta.setNegativeButton("Cancelar", null);
                alerta.create();
                alerta.show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return estantes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumEstante, textViewLetraEstante, textViewColorEstante;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNumEstante = itemView.findViewById(R.id.textViewNumEstante);
            textViewLetraEstante = itemView.findViewById(R.id.textViewLetraEstante);
            textViewColorEstante = itemView.findViewById(R.id.textViewColorEstante);
        }

        public void cargarEstantes(Estante estante) {
            textViewNumEstante.setText("Número: "+ estante.getNumero());
            textViewLetraEstante.setText("Letra: "+ estante.getLetra());
            textViewColorEstante.setText("Color: "+ estante.getColor());
        }
    }
}
