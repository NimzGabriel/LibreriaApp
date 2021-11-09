package com.ggomez.libreriaapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ggomez.libreriaapp.AutorMainActivity;
import com.ggomez.libreriaapp.MainActivity;
import com.ggomez.libreriaapp.ModificarAutorActivity;
import com.ggomez.libreriaapp.R;
import com.ggomez.libreriaapp.models.Autor;
import com.ggomez.libreriaapp.sqlite.DBAutor;

import java.util.ArrayList;

public class AutorAdapter extends RecyclerView.Adapter<AutorAdapter.ViewHolder> {
    ArrayList<Autor> autores;

    public AutorAdapter(ArrayList<Autor> autores) {
        this.autores = autores;
    }

    @NonNull
    @Override
    public AutorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_autor, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AutorAdapter.ViewHolder holder, int position) {
        holder.cargarAutores(autores.get(position));
        Context context = holder.itemView.getContext();

        // Modificar
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Autor autor = autores.get(position);
                Intent intent = new Intent(context, ModificarAutorActivity.class);
                intent.putExtra("autor", autor);
                context.startActivity(intent);
                ((AutorMainActivity)context).finish();
            }
        });

        // Eliminar
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setTitle("Eliminar Autor");
                alerta.setMessage("¿Seguro que desea eliminar el autor?");
                alerta.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Autor autor = autores.get(position);
                        DBAutor dbAutor = new DBAutor(context);
                        int result = dbAutor.eliminarAutor(autor.getId_autor());

                        if(result > 0) {
                            Toast.makeText(context, "Autor eliminado exitosamente", Toast.LENGTH_LONG).show();
                            context.startActivity(new Intent(context, AutorMainActivity.class));
                            ((AutorMainActivity)context).finish();
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
        return autores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombreAutor, textViewApellidosAutor, textViewNacionalidadAutor;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNombreAutor = itemView.findViewById(R.id.textViewNombreAutor);
            textViewApellidosAutor = itemView.findViewById(R.id.textViewApellidosAutor);
            textViewNacionalidadAutor = itemView.findViewById(R.id.textViewNacionalidadAutor);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void cargarAutores(Autor a) {
            textViewNombreAutor.setText("Nombre: " + a.getNombre());
            textViewApellidosAutor.setText("Apellidos: " + a.getApellidos());
            textViewNacionalidadAutor.setText("Nacionalidad: " + a.getNacionalidad());

            imageView.setImageResource(R.drawable.author_imagen);
        }
    }
}
