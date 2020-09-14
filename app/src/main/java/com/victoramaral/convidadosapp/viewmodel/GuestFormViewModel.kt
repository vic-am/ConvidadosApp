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

    private var mutableGuest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = mutableGuest

    fun save(guestId: Int, name: String, presence: Boolean) {
        val guest = GuestModel(guestId, name, presence)

        if (guestId == 0) {
            mutableSaveGuest.value = guestRepository.save(guest)
        } else {
            mutableSaveGuest.value = guestRepository.update(guest)
        }
    }

    fun load(id: Int) {
        mutableGuest.value = guestRepository.getGuestById(id)
    }
}