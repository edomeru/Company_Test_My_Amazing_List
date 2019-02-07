package com.app.farm.farmapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment: Fragment(){
    private var adapter: HobbiesAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = HobbiesAdapter(this!!.activity!!, Supplier.hobbies)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView =  inflater!!.inflate(R.layout.home_fragment, container, false)


       val recyclerview = rootView.findViewById(R.id.recyclerView) as RecyclerView // Add this
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = adapter
        //recyclerview.adapter = HobbiesAdapter(this!!.activity!!, Supplier.hobbies)
        return rootView


       // setupRecyclerView()

    }

    private fun setupRecyclerView() {

        val layoutManager = LinearLayoutManager(activity)

        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.isHorizontalFadingEdgeEnabled = true


        val adapter= HobbiesAdapter(this!!.activity!!, Supplier.hobbies)
        recyclerView.adapter = adapter


    }
}