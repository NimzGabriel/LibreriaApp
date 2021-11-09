package com.ggomez.libreriaapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.ggomez.libreriaapp.models.Autor;
import com.ggomez.libreriaapp.models.Editorial;

import java.util.ArrayList;

public class DBEditorial extends DBHelper{
    Context context;

    public DBEditorial(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    // INSERT
    public long insertarEditorial(Editorial editorial) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", editorial.getNombre());
        values.put("nacionalidad", editorial.getNacionalidad());

        return db.insert(DBHelper.TABLA_EDITORIALES, null, values);
    }

    // GET
    public ArrayList obtenerEditoriales() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Editorial> editorials = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + DBHelper.TABLA_EDITORIALES, null);

        if(cursor.moveToFirst()) {
            do {
                Editorial editorial = new Editorial(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                editorials.add(editorial);
            } while(cursor.moveToNext());

            return editorials;
        }

        return null;
    }

    public Editorial obtenerEditorialPorId(int id_editorial) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_EDITORIALES + " WHERE id_editorial = ?", new String[] {String.valueOf( id_editorial )});

        if(cursor.moveToFirst()){
            Editorial editorial = new Editorial();
            editorial.setId_editorial(cursor.getInt(0));
            editorial.setNombre(cursor.getString(1));

            return editorial;
        }

        return null;
    }

    // DELETE
    public int eliminarEditorial(int id_editorial) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return db.delete(TABLA_EDITORIALES, "id_editorial = ?", new String[] { String.valueOf(id_editorial)});
    }

    // UPDATE
    public int actualizarEditorial(int id_editorial, String nombre, String nacionalidad) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("nacionalidad", nacionalidad);

        return db.update(TABLA_EDITORIALES, values, "id_editorial = ?", new String[] {String.valueOf(id_editorial)});
    }

}
