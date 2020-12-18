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

        return try {
            val db = guestDatabaseHelper.readableDatabase

            val projection = arrayOf(
                DatabaseConstants.GUEST.COLUMNS.ID,
                DatabaseConstants.GUEST.COLUMNS.NAME,
                DatabaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                DatabaseConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.PRESENCE)) == 1

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }
            cursor.close()

            list

        } catch (e: Exception) {
            Log.e(
                DatabaseConstants.GUEST.ERROR.DATABASE_ERROR,
                "There was an error loading the guests"
            )
            list
        }
    }

    fun getPresentsGuests(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = guestDatabaseHelper.readableDatabase

            val projection = arrayOf(
                DatabaseConstants.GUEST.COLUMNS.ID,
                DatabaseConstants.GUEST.COLUMNS.NAME,
                DatabaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val selection = DatabaseConstants.GUEST.COLUMNS.PRESENCE + " = ?"
            val args = arrayOf("1")

            val cursor = db.query(
                DatabaseConstants.GUEST.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.PRESENCE)) == 1

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }
            cursor.close()

            list

        } catch (e: Exception) {
            Log.e(
                DatabaseConstants.GUEST.ERROR.DATABASE_ERROR,
                "There was an error updating the guests"
            )
            list
        }
    }

    fun getAbsentsGuests(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = guestDatabaseHelper.readableDatabase

            val projection = arrayOf(
                DatabaseConstants.GUEST.COLUMNS.ID,
                DatabaseConstants.GUEST.COLUMNS.NAME,
                DatabaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val selection = DatabaseConstants.GUEST.COLUMNS.PRESENCE + " = ?"
            val args = arrayOf("0")

            val cursor = db.query(
                DatabaseConstants.GUEST.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.PRESENCE)) == 1

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }
            cursor.close()

            list

        } catch (e: Exception) {
            Log.e(
                DatabaseConstants.GUEST.ERROR.DATABASE_ERROR,
                "There was an error updating the guests"
            )
            list
        }
    }

    fun getGuestById(id: Int): GuestModel? {

        var guest: GuestModel? = null
        return try {
            val db = guestDatabaseHelper.readableDatabase

            val projection = arrayOf(
                DatabaseConstants.GUEST.COLUMNS.NAME,
                DatabaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val selection = DatabaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DatabaseConstants.GUEST.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()

                val name =
                    cursor.getString(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME))
                val presence =
                    cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.PRESENCE)) == 1

                guest = GuestModel(id, name, presence)
            }

            cursor.close()

            guest

        } catch (e: Exception) {
            Log.e(
                DatabaseConstants.GUEST.ERROR.DATABASE_ERROR,
                "There was an error updating the guest"
            )
            guest
        }
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

    fun delete(id: Int): Boolean {
        return try {
            val db = guestDatabaseHelper.writableDatabase

            val selection = DatabaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DatabaseConstants.GUEST.TABLE_NAME, selection, args)
            true

        } catch (e: Exception) {
            Log.e(
                DatabaseConstants.GUEST.ERROR.DATABASE_ERROR,
                "There was an error deleting the guest"
            )
            false
        }
    }


}