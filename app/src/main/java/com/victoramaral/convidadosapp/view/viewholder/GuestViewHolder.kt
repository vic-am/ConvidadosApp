package com.victoramaral.convidadosapp.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.victoramaral.convidadosapp.R
import com.victoramaral.convidadosapp.service.model.GuestModel
import com.victoramaral.convidadosapp.view.listener.GuestListener

class GuestViewHolder(itemView: View, private val listener: GuestListener) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(guest: GuestModel) {
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name

        textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        textName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setMessage("Tem certeza que deseja excluir o convidado ${guest.name}?")
                .setPositiveButton("Excluir") { dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNegativeButton("Cancelar", null)
                .show()

            true
        }
    }

}