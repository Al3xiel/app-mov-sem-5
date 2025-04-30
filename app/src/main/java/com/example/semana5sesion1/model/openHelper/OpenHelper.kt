package com.example.semana5sesion1.model.openHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.semana5sesion1.model.Usuario
import android.content.ContentValues

class OpenHelper(context:Context): SQLiteOpenHelper(
    context, "usuarios.db",null,1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table users(_ID integer primary key autoincrement, nombre text, edad integer, mail text)"
        db?.execSQL(query)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        val query= "drop table users"
        db?.execSQL(query)
        onCreate(db)
    }

    fun newUser(usu: Usuario){
        val datos = ContentValues()
        datos.put("nombre",usu.nombre)
        datos.put("edad", usu.edad)
        datos.put("mail", usu.mail)
        this.writableDatabase.insert("users", null, datos)
    }

    fun readData(): MutableList<Usuario>{
        val db = this.readableDatabase
        val query = "select * from users"
        val result = db.rawQuery(query,null)
        val listaUsu=mutableListOf<Usuario>()

        if(result.moveToFirst()){
            do{
                listaUsu.add(
                    Usuario(result.getInt(0),result.getString(1),
                        result.getInt(2),result.getString(3))
                )
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return listaUsu
    }

    fun deleteUser(id: Int){
        val db = this.writableDatabase
        db.delete("users", "_ID=?", arrayOf(id.toString()))
        db.close()
    }
}
