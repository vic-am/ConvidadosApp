package com.victoramaral.convidadosapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.victoramaral.convidadosapp.service.model.GuestModel
import com.victoramaral.convidadosapp.service.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private val repository = GuestRepository.getInstance(context)

    private val mutableGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mutableGuestList

    fun load() {
        mutableGuestList.value = repository.getAllGuests()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

}