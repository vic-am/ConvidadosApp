package com.victoramaral.convidadosapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.victoramaral.convidadosapp.R
import com.victoramaral.convidadosapp.service.constants.GuestConstants.Companion.GUESTID
import com.victoramaral.convidadosapp.view.activity.GuestFormActivity
import com.victoramaral.convidadosapp.view.adapter.GuestAdapter
import com.victoramaral.convidadosapp.view.listener.GuestListener
import com.victoramaral.convidadosapp.viewmodel.PresentsViewModel

class PresentsFragment : Fragment() {

    private lateinit var viewModel: PresentsViewModel
    private val adapter = GuestAdapter()
    private lateinit var listener: GuestListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProviders.of(this).get(PresentsViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_presents, container, false)
        initRecycler(view)
        initListener()
        observer()


        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    private fun initRecycler(view: View) {
        val recycler = view.findViewById<RecyclerView>(R.id.recycler_presents)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
    }

    private fun initListener() {
        listener = object : GuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GUESTID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.load()
            }
        }
        adapter.attachListener(listener)
    }

    private fun observer() {
        viewModel.guestList.observe(viewLifecycleOwner, Observer {
            adapter.updateGuests(it)
        })
    }

}