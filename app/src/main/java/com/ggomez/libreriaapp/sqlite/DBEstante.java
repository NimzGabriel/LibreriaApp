package com.ggomez.libreriaapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.ggomez.libreriaapp.models.Editorial;
import com.ggomez.libreriaapp.models.Estante;

import java.util.ArrayList;

public class DBEstante extends DBHelper{
    Context context;

    public DBEstante(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    // INSERT
    public long insertarEstante(Estante estante) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("letra", estante.getLetra());
        values.put("numero", estante.getNumero());
        values.put("color", estante.getColor());

        return db.insert(DBHelper.TABLA_ESTANTES, null, values);
    }

    // GET
    public ArrayList obtenerEstantes() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Estante> estantes = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + DBHelper.TABLA_ESTANTES, null);

        if(cursor.moveToFirst()) {
            do {
                Estante estante = new Estante(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3)
                );
                estantes.add(estante);
            } while(cursor.moveToNext());

            return estantes;
        }

        return null;
    }

    public Estante obtenerEstantePorId(int id_estante) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_ESTANTES + " WHERE id_estante = ?", new String[] {String.valueOf( id_estante )});

        if(cursor.moveToFirst()){
            Estante estante = new Estante();
            estante.setId_estante(cursor.getInt(0));
            estante.setLetra(cursor.getString(1));
            estante.setNumero(cursor.getInt(2));
            estante.setColor(cursor.getString(3));

            return estante;
        }

        return null;
    }

    // DELETE
    public int eliminarEstante(int id_estante) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return db.delete(TABLA_ESTANTES, "id_estante = ?", new String[] { String.valueOf(id_estante)});
    }

    // UPDATE
    public int actualizarEditorial(int id_estante, String letra, int numero,String color) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("letra", letra);
        values.put("numero", numero);
        values.put("color", color);

        return db.update(TABLA_ESTANTES, values, "id_estante = ?", new String[] {String.valueOf(id_estante)});
    }


}
