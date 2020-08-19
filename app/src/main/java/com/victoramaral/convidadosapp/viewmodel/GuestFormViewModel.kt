package com.victoramaral.convidadosapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.victoramaral.convidadosapp.service.model.GuestModel
import com.victoramaral.convidadosapp.service.repository.GuestRepository

class GuestFormViewModel : ViewModel() {

    private val guestRepository = GuestRepository()

    private var mutableSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mutableSaveGuest

    fun save(name: String, presence: Boolean) {
        val guest = GuestModel(name, presence)
        guestRepository.save(guest)
    }
}