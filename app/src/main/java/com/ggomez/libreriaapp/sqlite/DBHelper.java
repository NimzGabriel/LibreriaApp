package com.ggomez.libreriaapp.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NOMBRE = "db_libreria";
    public static final int DB_VERSION = 1;
    // Nombres de tablas
    public static final String TABLA_AUTORES = "autores";
    public static final String TABLA_EDITORIALES = "editoriales";
    public static final String TABLA_ESTANTES= "estantes";
    public static final String TABLA_LIBROS= "libros";


    public DBHelper(@Nullable Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLA_AUTORES + "(id_autor integer primary key autoincrement, nombre text not null, apellidos text not null, nacionalidad text not null)");

        db.execSQL("create table " + TABLA_EDITORIALES + "(id_editorial integer primary key autoincrement, nombre text not null, nacionalidad text not null)");

        db.execSQL("create table " + TABLA_ESTANTES + "(id_estante integer primary key autoincrement, letra text not null, numero integer not null, color text not null)");

        db.execSQL("create table " + TABLA_LIBROS + "(id_libro integer primary key autoincrement, titulo text not null, descripcion text not null, fecha_publicacion text not null, copias integer not null, cantidad_paginas integer not null, id_autor integer not null, id_editorial integer not null, id_estante integer not null, foreign key (id_autor) references autores(id_autor), foreign key (id_editorial) references editoriales(id_editorial), foreign key (id_estante) references estantes(id_estante))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLA_AUTORES);
        db.execSQL("drop table if exists " + TABLA_EDITORIALES);
        db.execSQL("drop table if exists " + TABLA_ESTANTES);
        db.execSQL("drop table if exists " + TABLA_LIBROS);

        onCreate(db);
    }
}
