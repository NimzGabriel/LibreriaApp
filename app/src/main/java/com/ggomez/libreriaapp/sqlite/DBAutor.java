package com.ggomez.libreriaapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.ggomez.libreriaapp.models.Autor;

import java.util.ArrayList;

public class DBAutor extends DBHelper{
    Context context;

    public DBAutor(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    // INSERT
    public long insertarAutor(Autor autor) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", autor.getNombre());
        values.put("apellidos", autor.getApellidos());
        values.put("nacionalidad", autor.getNacionalidad());

        return db.insert(DBHelper.TABLA_AUTORES, null, values);
    }

    // GET
    public ArrayList obtenerAutores() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Autor> autores = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + DBHelper.TABLA_AUTORES, null);

        if(cursor.moveToFirst()) {
            do {
                Autor autor = new Autor(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                autores.add(autor);
            } while(cursor.moveToNext());

            return autores;
        }
        else {
            return null;
        }
    }

    public Autor obtenerAutorPorId(int id_autor) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLA_AUTORES +" WHERE id_autor = ?", new String[] {String.valueOf( id_autor )});

        if(cursor.moveToFirst()){
            Autor autor = new Autor();
            autor.setId_autor(cursor.getInt(0));
            autor.setNombre(cursor.getString(1));

            return autor;
        }

        return null;
    }

    // DELETE
    public int eliminarAutor(int id_autor) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return db.delete(TABLA_AUTORES, "id_autor = ?", new String[] { String.valueOf(id_autor)});
    }

    // UPDATE
    public int actualizarAutor(int id_autor, String nombre, String apellidos, String nacionalidad) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellidos", apellidos);
        values.put("nacionalidad", nacionalidad);

       return db.update(TABLA_AUTORES, values, "id_autor = ?", new String[] {String.valueOf(id_autor)});
    }
}
