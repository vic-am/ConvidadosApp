package com.victoramaral.convidadosapp.view.activity

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.victoramaral.convidadosapp.R
import com.victoramaral.convidadosapp.service.constants.GuestConstants
import com.victoramaral.convidadosapp.viewmodel.GuestFormViewModel
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), OnClickListener {

    private lateinit var viewModel: GuestFormViewModel
    private var guestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)
        setListeners()
        observe()
        loadData()
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.buttonSave -> {

                val name = editTextName.text.toString()
                val presence = radioPresent.isChecked
                viewModel.save(guestId, name, presence)
                finish()
            }
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(GuestConstants.GUESTID)
            viewModel.load(guestId)
        }
    }

    private fun observe() {
        viewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(
                    applicationContext,
                    "Sucesso ao salvar o convidado",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Falha ao salvar o convidado",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        viewModel.guest.observe(this, Observer {
            editTextName.setText(it.name)
            if (it.presence) {
                radioPresent.isChecked = true
            } else {
                radioAbsent.isChecked = true
            }
        })
    }

    private fun setListeners() {
        buttonSave.setOnClickListener(this)
    }
}