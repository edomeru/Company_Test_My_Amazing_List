package com.app.farm.farmapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main_nav.*


class MainNavActivity : AppCompatActivity() {

   val manager = supportFragmentManager

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

            val transaction =    manager.beginTransaction()
             val homeFragment =   HomeFragment()
                transaction.replace(R.id.frame_view,homeFragment)
                transaction.addToBackStack(null)
                transaction.commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                //message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
               // message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val transaction =    manager.beginTransaction()
        val homeFragment =   HomeFragment()
        transaction.replace(R.id.frame_view,homeFragment)
        transaction.addToBackStack(null)
        transaction.commit()

       // setupRecyclerView()
    }

//    private fun setupRecyclerView() {
//
//        val layoutManager = LinearLayoutManager(this)
//        layoutManager.orientation = LinearLayoutManager.VERTICAL
//        recyclerView.layoutManager = layoutManager
//       recyclerView.isHorizontalFadingEdgeEnabled = true
//
//
//        val adapter= HobbiesAdapter(this, Supplier.hobbies)
//        recyclerView.adapter = adapter
//
//
//    }

}


