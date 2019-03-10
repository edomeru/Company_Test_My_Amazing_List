package com.app.farm.farmapp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.home_fragment.*
import okhttp3.*
import java.io.IOException






class HomeFragment: Fragment(){

    private var homeFeed:HomeFeed? = null
    private var recyclerview:RecyclerView? = null
    private var homeadapter: HobbiesAdapter? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//       // homeadapter = HobbiesAdapter(this!!.activity!!, homeFeed!!)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView =  inflater.inflate(R.layout.home_fragment, container, false)


        recyclerview = rootView.findViewById(R.id.recyclerView) as RecyclerView
        this.recyclerview!!.layoutManager = LinearLayoutManager(activity)

        fetchJson()

//        recyclerview.adapter = adapter
        //recyclerview.adapter = HobbiesAdapter(this!!.activity!!, Supplier.hobbies)
        return rootView


       // setupRecyclerView()

    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        recyclerview.apply {
//
//            this!!.layoutManager = LinearLayoutManager(activity)
//          //  adapter = HobbiesAdapter(context, this@HomeFragment.homeFeed!!)
//            this.adapter = this@HomeFragment.homeadapter
//        }
//    }

//    private fun setupRecyclerView() {
//
//        val layoutManager = LinearLayoutManager(activity)
//
//        layoutManager.orientation = LinearLayoutManager.VERTICAL
//        recyclerView.layoutManager = layoutManager
//        recyclerView.isHorizontalFadingEdgeEnabled = true
//
//
//        val adapter= HobbiesAdapter(this!!.activity!!, Supplier.hobbies)
//        recyclerView.adapter = adapter
//
//
//    }

    fun fetchJson() {
        println("Attempting to Fetch JSON")

        val url = "http://splaneg.atwebpages.com/Lebsys/webservice/showall.php?user_id=1"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()

                println("Successful request $body")
                println(body)

                val gson = GsonBuilder().create()

                homeFeed = gson.fromJson(body, HomeFeed::class.java)
                println("gson request $homeFeed")
                homeadapter = HobbiesAdapter(activity!!, homeFeed!!)

                getActivity()!!.runOnUiThread(
                    {
                        recyclerview!!.adapter = homeadapter

                    })

            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })
    }

    class HomeFeed(val post: List<Post>)

    class Post(val wall_id: Int, val post_pic: String, val caption: String, val post_date: String)

}