package com.victoramaral.convidadosapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.victoramaral.convidadosapp.R
import com.victoramaral.convidadosapp.service.model.GuestModel
import com.victoramaral.convidadosapp.view.listener.GuestListener
import com.victoramaral.convidadosapp.view.viewholder.GuestViewHolder

class GuestAdapter() : RecyclerView.Adapter<GuestViewHolder>() {

    private var guestList: List<GuestModel> = arrayListOf()
    private lateinit var listener: GuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {

        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_guest, parent, false)

        return GuestViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updateGuests(list: List<GuestModel>) {
        guestList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: GuestListener) {
        this.listener = listener
    }
}