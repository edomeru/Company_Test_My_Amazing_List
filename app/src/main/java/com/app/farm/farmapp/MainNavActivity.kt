package com.app.farm.farmapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main_nav.*


class MainNavActivity : AppCompatActivity() extends Fragment  implements OnUpdateListener, OnLoadListener {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        //recyclerView.layoutM = layoutManager
       recyclerView.isHorizontalFadingEdgeEnabled = true


        val adapter= HobbiesAdapter(this, Supplier.hobbies)
        recyclerView.adapter
        recyclerView.setFooterDividersEnabled(true)
        recyclerView.setOnUpdateListener(this).setOnLoadListener(this)
        recyclerView.requestUpdate()
    }

}


