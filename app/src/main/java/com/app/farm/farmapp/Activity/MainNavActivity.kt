package com.app.farm.farmapp.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.app.farm.farmapp.*
import com.app.farm.farmapp.Adapter.MessagesAdapter
import com.app.farm.farmapp.Adapter.PicsAdapter
import com.app.farm.farmapp.ViewHolder.RecyclerViewHolder
import com.app.farm.farmapp.ViewHolder.RecyclerViewHolder2
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class MainNavActivity : AppCompatActivity() , RecyclerViewHolder.ItemClickListener, RecyclerViewHolder2.ItemClickListener2 {


    private var messageFeed: MessagesFeed? = null
    private var recyclerview: RecyclerView? = null
    private var recyclerview2: RecyclerView? = null
    private var messagesadapter: MessagesAdapter? = null
    private var picsAdapter: PicsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

        recyclerview2 = findViewById(R.id.recyclerView2) as RecyclerView
        recyclerview = findViewById(R.id.recyclerView) as RecyclerView
        this.recyclerview!!.layoutManager = LinearLayoutManager(this)
        this.recyclerview2!!.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)

        fetchJsonForHorizontalRecyclerView()
        fetchJsonForVerticalRecyclerView()

        // Refreshes every 60 seconds
        val handler = Handler()
        val runnableCode = object : Runnable {
            override fun run() {

                Log.d("Handlers", "Called on main thread")
                fetchJsonForHorizontalRecyclerView()
                handler.postDelayed(this, 60000)//60 seconds
            }
        }

        handler.post(runnableCode)
    }

    fun fetchJsonForHorizontalRecyclerView() {
        println("Attempting to Fetch JSON")

        val request = Request.Builder().url(Constants.url.plus(Constants.show_all_pics)).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()

                println("Successful request pic $body")

                val gson = GsonBuilder().create()

                messageFeed = gson.fromJson(body, MessagesFeed::class.java)
                println("gson request $messageFeed")
                picsAdapter =
                    PicsAdapter(
                        this@MainNavActivity!!,
                        this@MainNavActivity!!,
                        messageFeed!!
                    )

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

        val request = Request.Builder().url(Constants.url.plus(Constants.show_all)).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()

                println("Successful request $body")

                val gson = GsonBuilder().create()

                messageFeed = gson.fromJson(body, MessagesFeed::class.java)
                println("gson request $messageFeed")
               messagesadapter =
                   MessagesAdapter(
                       this@MainNavActivity!!, this@MainNavActivity,
                       messageFeed!!
                   )

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

   //interface method Horizontal RecyclerView
    override fun onItemClick(view: View, position: Int) {

        val intent = Intent(this, Activity_WebV::class.java)
        intent.putExtra("Position", position)
        startActivity(intent)
    }
    //interface method Vertical RecyclerView
    override fun onItemClick2(view: View, position: Int) {
        Log.d("onItemClick2", "POS $position")
    }

    //Data Model Classes
    class MessagesFeed(val post: List<Post>)

    class Post(val wall_id: Int, val post_pic: String, val caption: String, val post_date: String, val name: String)

}


