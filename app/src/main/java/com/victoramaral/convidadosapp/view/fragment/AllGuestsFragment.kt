package com.victoramaral.convidadosapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.victoramaral.convidadosapp.R
import com.victoramaral.convidadosapp.service.model.GuestModel
import com.victoramaral.convidadosapp.view.adapter.GuestAdapter
import com.victoramaral.convidadosapp.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private var guestList: List<GuestModel> = listOf()
    private val adapter = GuestAdapter(guestList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_all_guests, container, false)
        initRecycler(view)

        observer()

        return view
    }

    override fun onResume() {
        super.onResume()
        allGuestsViewModel.load()

    }

    private fun initRecycler(view: View) {
        val recycler = view.findViewById<RecyclerView>(R.id.recycler_all_guests)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
    }

    private fun observer() {
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            adapter.updateGuests(it)
        })
    }
}