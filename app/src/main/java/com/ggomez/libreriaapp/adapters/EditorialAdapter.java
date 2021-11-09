package com.ggomez.libreriaapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ggomez.libreriaapp.AutorMainActivity;
import com.ggomez.libreriaapp.EditorialMainActivity;
import com.ggomez.libreriaapp.ModificarAutorActivity;
import com.ggomez.libreriaapp.ModificarEditorialActivity;
import com.ggomez.libreriaapp.R;
import com.ggomez.libreriaapp.models.Autor;
import com.ggomez.libreriaapp.models.Editorial;
import com.ggomez.libreriaapp.sqlite.DBAutor;
import com.ggomez.libreriaapp.sqlite.DBEditorial;

import java.util.ArrayList;

public class EditorialAdapter extends RecyclerView.Adapter<EditorialAdapter.ViewHolder> {
    ArrayList<Editorial> editoriales;

    public EditorialAdapter(ArrayList<Editorial> editoriales) {
        this.editoriales = editoriales;
    }

    @NonNull
    @Override
    public EditorialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_editorial, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cargarEditoriales(editoriales.get(position));
        Context context = holder.itemView.getContext();

        // Modificar
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editorial editorial = editoriales.get(position);
                Intent intent = new Intent(context, ModificarEditorialActivity.class);
                intent.putExtra("editorial", editorial);
                context.startActivity(intent);
                ((EditorialMainActivity)context).finish();
            }
        });


        // Eliminar
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setTitle("Eliminar Editorial");
                alerta.setMessage("¿Seguro que desea eliminar la editorial?");
                alerta.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Editorial editorial = editoriales.get(position);
                        DBEditorial dbEditorial = new DBEditorial(context);
                        int result = dbEditorial.eliminarEditorial(editorial.getId_editorial());

                        if(result > 0) {
                            Toast.makeText(context, "Editorial eliminada exitosamente", Toast.LENGTH_LONG).show();
                            context.startActivity(new Intent(context, EditorialMainActivity.class));
                            ((EditorialMainActivity)context).finish();
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
        return editoriales.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombreEditorial, textViewNacionalidadEditorial;
        ImageView imageViewEditorial;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNombreEditorial = itemView.findViewById(R.id.textViewNombreEditorial);
            textViewNacionalidadEditorial = itemView.findViewById(R.id.textViewNacionalidadEditorial);
            imageViewEditorial = itemView.findViewById(R.id.imageViewEditorial);
        }

        public void cargarEditoriales(Editorial e) {
            textViewNombreEditorial.setText("Nombre: " + e.getNombre());
            textViewNacionalidadEditorial.setText("Nacionalidad: " + e.getNacionalidad());

            imageViewEditorial.setImageResource(R.drawable.editorial_imagen);
        }
    }
}
