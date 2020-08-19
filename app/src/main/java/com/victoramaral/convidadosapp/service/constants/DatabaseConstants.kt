package com.victoramaral.convidadosapp.service.constants

class DatabaseConstants private constructor() {

    object GUEST {
        const val TABLE_NAME = "Guest"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }

        object ERROR{
            const val DATABASE_ERROR = "GUEST DATABASE ERROR"
        }
    }
}