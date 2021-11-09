package com.ggomez.libreriaapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.ggomez.libreriaapp.models.Autor;
import com.ggomez.libreriaapp.models.Editorial;
import com.ggomez.libreriaapp.models.Estante;
import com.ggomez.libreriaapp.models.Libro;

import java.util.ArrayList;

public class DBLibro extends DBHelper{
    Context context;

    public DBLibro(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    // INSERT
    public long insertarLibro(String titulo, String descripcion, String fecha, int copias, int paginas,
                              int id_autor, int id_editorial, int id_estante) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("titulo", titulo);
        values.put("descripcion", descripcion);
        values.put("fecha_publicacion", fecha);
        values.put("copias", copias);
        values.put("cantidad_paginas", paginas);
        values.put("id_autor", id_autor);
        values.put("id_editorial", id_editorial);
        values.put("id_estante", id_estante);

        return db.insert(DBHelper.TABLA_LIBROS, null, values);
    }

    // GET
    public ArrayList<Libro> obtenerLibros(){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Libro> libros = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_LIBROS, null);

        DBAutor dbAutor = new DBAutor(context);
        DBEditorial dbEditorial = new DBEditorial(context);
        DBEstante dbEstante = new DBEstante(context);

        if(cursor.moveToFirst()){
            do{
                Autor autor = dbAutor.obtenerAutorPorId(cursor.getInt(6));
                Editorial editorial = dbEditorial.obtenerEditorialPorId(cursor.getInt(7));
                Estante estante = dbEstante.obtenerEstantePorId(cursor.getInt(8));

                Libro libro = new Libro();
                libro.setId_libro(cursor.getInt(0));
                libro.setTitulo(cursor.getString(1));
                libro.setDescripcion(cursor.getString(2));
                libro.setFecha(cursor.getString(3));
                libro.setCopias(cursor.getInt(4));
                libro.setPaginas(cursor.getInt(5));
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setEstante(estante);

                libros.add(libro);

            } while (cursor.moveToNext());

            return libros;
        }

        return null;
    }

    // Filtro autor
    public ArrayList<Libro> obtenerLibrosFiltroAutor(int id_autor){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Libro> libros = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_LIBROS + " WHERE id_autor = ?", new String[]{String.valueOf(id_autor)});

        DBAutor dbAutor = new DBAutor(context);
        DBEditorial dbEditorial = new DBEditorial(context);
        DBEstante dbEstante = new DBEstante(context);

        if(cursor.moveToFirst()){
            do{
                Autor autor = dbAutor.obtenerAutorPorId(cursor.getInt(6));
                Editorial editorial = dbEditorial.obtenerEditorialPorId(cursor.getInt(7));
                Estante estante = dbEstante.obtenerEstantePorId(cursor.getInt(8));

                Libro libro = new Libro();
                libro.setId_libro(cursor.getInt(0));
                libro.setTitulo(cursor.getString(1));
                libro.setDescripcion(cursor.getString(2));
                libro.setFecha(cursor.getString(3));
                libro.setCopias(cursor.getInt(4));
                libro.setPaginas(cursor.getInt(5));
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setEstante(estante);

                libros.add(libro);

            } while (cursor.moveToNext());

            return libros;
        }

        return null;
    }

    // Filtro Editorial
    public ArrayList<Libro> obtenerLibrosFiltroEditorial(int id_editorial){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Libro> libros = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_LIBROS + " WHERE id_editorial = ?", new String[]{String.valueOf(id_editorial)});

        DBAutor dbAutor = new DBAutor(context);
        DBEditorial dbEditorial = new DBEditorial(context);
        DBEstante dbEstante = new DBEstante(context);

        if(cursor.moveToFirst()){
            do{
                Autor autor = dbAutor.obtenerAutorPorId(cursor.getInt(6));
                Editorial editorial = dbEditorial.obtenerEditorialPorId(cursor.getInt(7));
                Estante estante = dbEstante.obtenerEstantePorId(cursor.getInt(8));

                Libro libro = new Libro();
                libro.setId_libro(cursor.getInt(0));
                libro.setTitulo(cursor.getString(1));
                libro.setDescripcion(cursor.getString(2));
                libro.setFecha(cursor.getString(3));
                libro.setCopias(cursor.getInt(4));
                libro.setPaginas(cursor.getInt(5));
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setEstante(estante);

                libros.add(libro);

            } while (cursor.moveToNext());

            return libros;
        }

        return null;
    }

    // Filtro por Autor y Editorial
    public ArrayList<Libro> filtroAutorYEditorial(int id_autor, int id_editorial){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Libro> libros = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_LIBROS + " WHERE id_autor = ? and id_editorial = ?", new String[]{String.valueOf(id_autor), String.valueOf(id_editorial)});

        DBAutor dbAutor = new DBAutor(context);
        DBEditorial dbEditorial = new DBEditorial(context);
        DBEstante dbEstante = new DBEstante(context);

        if(cursor.moveToFirst()){
            do{
                Autor autor = dbAutor.obtenerAutorPorId(cursor.getInt(6));
                Editorial editorial = dbEditorial.obtenerEditorialPorId(cursor.getInt(7));
                Estante estante = dbEstante.obtenerEstantePorId(cursor.getInt(8));

                Libro libro = new Libro();
                libro.setId_libro(cursor.getInt(0));
                libro.setTitulo(cursor.getString(1));
                libro.setDescripcion(cursor.getString(2));
                libro.setFecha(cursor.getString(3));
                libro.setCopias(cursor.getInt(4));
                libro.setPaginas(cursor.getInt(5));
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setEstante(estante);

                libros.add(libro);

            } while (cursor.moveToNext());

            return libros;
        }

        return null;
    }


    // Filtro por titulo del libro
    public ArrayList<Libro> obtenerLibrosFiltroTitulo(String titulo){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Libro> libros = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_LIBROS + " WHERE titulo = ?", new String[]{titulo});

        DBAutor dbAutor = new DBAutor(context);
        DBEditorial dbEditorial = new DBEditorial(context);
        DBEstante dbEstante = new DBEstante(context);

        if(cursor.moveToFirst()){
            do{
                Autor autor = dbAutor.obtenerAutorPorId(cursor.getInt(6));
                Editorial editorial = dbEditorial.obtenerEditorialPorId(cursor.getInt(7));
                Estante estante = dbEstante.obtenerEstantePorId(cursor.getInt(8));

                Libro libro = new Libro();
                libro.setId_libro(cursor.getInt(0));
                libro.setTitulo(cursor.getString(1));
                libro.setDescripcion(cursor.getString(2));
                libro.setFecha(cursor.getString(3));
                libro.setCopias(cursor.getInt(4));
                libro.setPaginas(cursor.getInt(5));
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setEstante(estante);

                libros.add(libro);

            } while (cursor.moveToNext());

            return libros;
        }

        return null;
    }


    // DELETE
    public int eliminarLibro(int id_libro) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return db.delete(TABLA_LIBROS, "id_libro = ?", new String[] { String.valueOf(id_libro)});
    }

    // UPDATE
    public int actualizarLibro(int id_libro, String titulo, String descripcion, String fecha_publicacion, int copias, int cantidad_paginas, Autor autor, Editorial editorial, Estante estante) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("titulo", titulo);
        values.put("descripcion", descripcion);
        values.put("fecha_publicacion", fecha_publicacion);
        values.put("copias", copias);
        values.put("cantidad_paginas", cantidad_paginas);

        values.put("id_autor", autor.getId_autor());
        values.put("id_editorial", editorial.getId_editorial());
        values.put("id_estante", estante.getId_estante());

        return db.update(TABLA_LIBROS, values, "id_libro = ?", new String[] {String.valueOf(id_libro)});
    }


}
