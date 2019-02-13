package com.app.farm.farmapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.register.*

class RegisterFragment: Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView =  inflater!!.inflate(R.layout.register, container, false)


//        register_button_register.setOnClickListener { rootView ->
//            val email =  email_edittext_register.text.toString()
//            val password =  password_edittext_register.text.toString()
//
//            Log.d("email",email)
//            Log.d("pass",password)
//
//        }


        return rootView


        // setupRecyclerView()

    }

}