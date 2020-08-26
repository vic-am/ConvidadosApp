package com.victoramaral.convidadosapp.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.victoramaral.convidadosapp.service.constants.DatabaseConstants

class GuestDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_GUEST)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private const val DATABASE_NAME = "Convidados.db"
        private const val DATABASE_VERSION = 1

        private const val CREATE_TABLE_GUEST =
            (
                    "create table " + DatabaseConstants.GUEST.TABLE_NAME + " ("
                            + DatabaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
                            + DatabaseConstants.GUEST.COLUMNS.NAME + " text, "
                            + DatabaseConstants.GUEST.COLUMNS.PRESENCE + " integer);"
                    )
    }

}