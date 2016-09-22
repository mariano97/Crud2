package com.usco.mariano.crud2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mariano on 22/09/16.
 */
public class BaseHeper extends SQLiteOpenHelper {

    //Se crea la tabla de la base de datos donde se van a almacenar los datos
    String tabla = "create table personas(id integer primary key autoincrement, nombre text, apellido text, barrio text)";
    public BaseHeper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //en este metodo se ejecuta el query para la creacion de la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla);
    }
    //se establece un upgrade para si dado el caso se necesita actualizar la tabla, este metodo la borre y la vuelva a crear
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table personas");
        db.execSQL(tabla);

    }
}
