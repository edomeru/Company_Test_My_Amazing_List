package com.app.farm.farmapp.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.webkit.WebView
import android.webkit.WebViewClient
import com.app.farm.farmapp.Constants
import com.app.farm.farmapp.R
import com.app.farm.farmapp.DataModel.detail
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class Activity_WebV : AppCompatActivity() {
    var mywebview: WebView? = null
    var position: Int = 0
    var detailFeed: detail? = null
    var actionbar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        //actionbar
        actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        //get position from main activity
        val bundle :Bundle ?=intent.extras
        if (bundle!=null){
            this.position = bundle.getInt("Position")
        }


       fetchJsonForDetailedView()


        mywebview = findViewById<WebView>(R.id.webview)
        mywebview!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun fetchJsonForDetailedView() {
        println("Attempting to Fetch JSON")

       var pos: Int = position + 1

        val url = "${Constants.url.plus(Constants.show_detail)}?wall_id=${pos.toString()}"
        println("URLLLL $url")
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()

                println("Successful request pic ${body.toString()}")

                val gson = GsonBuilder().create()

                this@Activity_WebV!!.runOnUiThread {
                    //recyclerview2!!.adapter = picsAdapter
                    detailFeed   = gson.fromJson(body, detail::class.java)
                   //println("gson request_NEW_ACT ${detailFeed!!.users.get(0).postPic}")
                    mywebview!!.loadUrl(detailFeed!!.users.get(0).postPic)
                    actionbar!!.title = detailFeed!!.users.get(0).name

                }

            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })
    }
}
