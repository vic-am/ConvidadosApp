package com.victoramaral.convidadosapp.view.listener

import com.victoramaral.convidadosapp.service.model.GuestModel

interface GuestListener {
    fun onClick(id: Int)
    fun onDelete(id: Int)
}