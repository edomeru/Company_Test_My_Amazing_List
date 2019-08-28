package com.app.farm.farmapp

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main_nav.*
import kotlinx.android.synthetic.main.list_item_chat2.*
import kotlinx.android.synthetic.main.list_item_chat2.view.*
import okhttp3.*
import java.io.IOException


class MainNavActivity : AppCompatActivity() ,RecyclerViewHolder.ItemClickListener, RecyclerViewHolder2.ItemClickListener{





    private var messageFeed: MainNavActivity.MessagesFeed? = null
    private var recyclerview: RecyclerView? = null
    private var recyclerview2: RecyclerView? = null
    private var messagesadapter: MessagesAdapter? = null
    private var picsAdapter: PicsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_messages)

        recyclerview2 = findViewById(R.id.recyclerView2) as RecyclerView
        recyclerview = findViewById(R.id.recyclerView) as RecyclerView
        this.recyclerview!!.layoutManager = LinearLayoutManager(this)
        this.recyclerview2!!.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)

        fetchJsonForHorizontalRecyclerView()
        fetchJsonForVerticalRecyclerView()


        val handler = Handler()
        val runnableCode = object : Runnable {
            override fun run() {Log.d("Handlers", "Called on main thread")

                fetchJsonForHorizontalRecyclerView()
                handler.postDelayed(this, 60000)
            }
        }

        handler.post(runnableCode)
    }

    fun fetchJsonForHorizontalRecyclerView() {
        println("Attempting to Fetch JSON")

        val url = "https://lebsysco.000webhostapp.com/showallPics.php"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()

                println("Successful request pic $body")
                println(body)

                val gson = GsonBuilder().create()

                messageFeed = gson.fromJson(body, MainNavActivity.MessagesFeed::class.java)
                println("gson request $messageFeed")
                picsAdapter = PicsAdapter(this@MainNavActivity!!, this@MainNavActivity!!,messageFeed!!)

                this@MainNavActivity!!.runOnUiThread(
                    {
                        recyclerview2!!.adapter = picsAdapter

                    })





            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })
    }


    fun fetchJsonForVerticalRecyclerView() {
        println("Attempting to Fetch JSON")

        val url = "https://lebsysco.000webhostapp.com/showall.php"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()

                println("Successful request $body")
                println(body)

                val gson = GsonBuilder().create()

                messageFeed = gson.fromJson(body, MainNavActivity.MessagesFeed::class.java)
                println("gson request $messageFeed")
               messagesadapter = MessagesAdapter(this@MainNavActivity!!, this@MainNavActivity,messageFeed!!)

                this@MainNavActivity!!.runOnUiThread(
                    {
                        recyclerview!!.adapter = messagesadapter

                    })

            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })
    }

    override fun onItemClick(view: View, position: Int) {

        val intent = Intent(this, Activity_WebV::class.java)
        intent.putExtra("Position", position)
        startActivity(intent)
    }

    class MessagesFeed(val post: List<Post>)

    class Post(val wall_id: Int, val post_pic: String, val caption: String, val post_date: String, val name: String)

}


