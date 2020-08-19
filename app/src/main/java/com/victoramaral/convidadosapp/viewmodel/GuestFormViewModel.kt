package com.victoramaral.convidadosapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.victoramaral.convidadosapp.service.model.GuestModel
import com.victoramaral.convidadosapp.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private val guestRepository = GuestRepository.getInstance(context)

    private var mutableSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mutableSaveGuest

    fun save(name: String, presence: Boolean) {
        val guest = GuestModel(name, presence)
        guestRepository.save(guest)
    }
}