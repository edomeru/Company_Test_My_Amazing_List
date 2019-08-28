package com.app.farm.farmapp.ViewHolder

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.farm.farmapp.Activity.MainNavActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_chat2.view.*

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //Listener
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)

    }


    fun setData(hobby: MainNavActivity.Post?, pos: Int, activity: AppCompatActivity) {


        hobby?.let {
            itemView.nme.text = hobby.name
            Glide.with(activity).load(hobby.post_pic).into(itemView.iv_user_photo_cv)
        }?: run {

        }
    }
}