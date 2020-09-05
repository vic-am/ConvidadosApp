package com.victoramaral.convidadosapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.victoramaral.convidadosapp.R
import com.victoramaral.convidadosapp.service.model.GuestModel
import com.victoramaral.convidadosapp.view.viewholder.GuestViewHolder

class GuestAdapter(private var guestList: List<GuestModel>) : RecyclerView.Adapter<GuestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {

        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_guest, parent, false)

        return GuestViewHolder(item)
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
}