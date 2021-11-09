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

import com.ggomez.libreriaapp.EstanteMainActivity;
import com.ggomez.libreriaapp.MainActivity;
import com.ggomez.libreriaapp.ModificarEstanteActivity;
import com.ggomez.libreriaapp.ModificarLibroActivity;
import com.ggomez.libreriaapp.R;
import com.ggomez.libreriaapp.models.Estante;
import com.ggomez.libreriaapp.models.Libro;
import com.ggomez.libreriaapp.sqlite.DBEstante;
import com.ggomez.libreriaapp.sqlite.DBLibro;

import java.util.ArrayList;

public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.ViewHolder> {
    ArrayList<Libro> libros;

    public LibroAdapter(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    @NonNull
    @Override
    public LibroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LibroAdapter.ViewHolder holder, int position) {
        holder.cargarLibros(libros.get(position));
        Context context = holder.itemView.getContext();

        // Modificar
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Libro libro = libros.get(position);
                Intent intent = new Intent(context, ModificarLibroActivity.class);
                intent.putExtra("libro", libro);
                context.startActivity(intent);
                ((MainActivity)context).finish();
            }
        });


        // Eliminar
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setTitle("Eliminar Libro");
                alerta.setMessage("¿Seguro que desea eliminar el libro?");
                alerta.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Libro libro = libros.get(position);
                        DBLibro dbLibro = new DBLibro(context);
                        int result = dbLibro.eliminarLibro(libro.getId_libro());

                        if(result > 0) {
                            Toast.makeText(context, "libro eliminado exitosamente", Toast.LENGTH_LONG).show();
                            context.startActivity(new Intent(context, MainActivity.class));
                            ((MainActivity)context).finish();
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
        return libros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTituloLibro, textViewDescripcionLibro, textViewFechaLibro,
                textViewCopiasLibro, textViewPaginasLibro, textViewAutorLibro,
                textViewEditorialLibro, textViewEstanteLibro;
        ImageView imageViewLibro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTituloLibro = itemView.findViewById(R.id.textViewTituloLibro);
            textViewDescripcionLibro = itemView.findViewById(R.id.textViewDescripcionLibro);
            textViewFechaLibro = itemView.findViewById(R.id.textViewFechaLibro);
            textViewCopiasLibro = itemView.findViewById(R.id.textViewCopiasLibro);
            textViewPaginasLibro = itemView.findViewById(R.id.textViewPaginasLibro);
            textViewAutorLibro = itemView.findViewById(R.id.textViewAutorLibro);

            textViewEditorialLibro = itemView.findViewById(R.id.textViewEditorialLibro);
            textViewEstanteLibro = itemView.findViewById(R.id.textViewEstanteLibro);

            imageViewLibro = itemView.findViewById(R.id.imageViewLibro);
        }

        public void cargarLibros(Libro libro) {
            textViewTituloLibro.setText("Título: " + libro.getTitulo());
            textViewDescripcionLibro.setText("Descripción: " + libro.getDescripcion());
            textViewFechaLibro.setText("Fecha: " + libro.getFecha());
            textViewCopiasLibro.setText("Copias: " + String.valueOf(libro.getCopias()));
            textViewPaginasLibro.setText("N. Páginas: " + String.valueOf(libro.getPaginas()));
            textViewAutorLibro.setText("Autor: " + libro.getAutor().getNombre());
            textViewEditorialLibro.setText("Editorial: " + libro.getEditorial().getNombre());
            textViewEstanteLibro.setText("Estante: " + libro.getEstante().getColor());

            imageViewLibro.setImageResource(R.drawable.libro_imagen);
        }
    }
}
