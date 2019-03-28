package com.mgoes.supermercado.listadesupermercado

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.*


class ListaComprasDatabase(context: Context) : ManagedSQLiteOpenHelper(ctx = context, name = "listaCompras.db", version = 1) {

    override fun onCreate(db: SQLiteDatabase){

        db.createTable("produto", true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "nome" to TEXT,
            "quantidade" to INTEGER,
            "valor" to REAL,
            "foto" to BLOB)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented")
    }
}