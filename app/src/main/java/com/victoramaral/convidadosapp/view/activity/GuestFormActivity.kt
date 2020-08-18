package com.victoramaral.convidadosapp.view.activity

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.victoramaral.convidadosapp.R
import com.victoramaral.convidadosapp.viewmodel.GuestFormViewModel
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), OnClickListener {

    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
    }

    private fun setListeners() {
        buttonSave.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.buttonSave -> {
                finish()
            }
        }

    }
}