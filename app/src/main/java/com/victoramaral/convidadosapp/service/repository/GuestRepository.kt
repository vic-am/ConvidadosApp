package com.victoramaral.convidadosapp.service.repository

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.victoramaral.convidadosapp.service.constants.DatabaseConstants
import com.victoramaral.convidadosapp.service.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private var guestDatabaseHelper = GuestDatabaseHelper(context)

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun getAllGuests(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getPresentsGuests(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getAbsentsGuests(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun save(guest: GuestModel): Boolean {

        return try {
            val db = guestDatabaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DatabaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DatabaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            db.insert(DatabaseConstants.GUEST.TABLE_NAME, null, contentValues)
            true

        } catch (e: Exception) {
            Log.e(
                DatabaseConstants.GUEST.ERROR.DATABASE_ERROR,
                "There was an error saving the guest"
            )
            false
        }

    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = guestDatabaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DatabaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DatabaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            val selection = DatabaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DatabaseConstants.GUEST.TABLE_NAME, contentValues, selection, args)
            true

        } catch (e: Exception) {
            Log.e(
                DatabaseConstants.GUEST.ERROR.DATABASE_ERROR,
                "There was an error updating the guest"
            )
            false
        }

    }

    fun delete(guest: GuestModel) {

    }


}