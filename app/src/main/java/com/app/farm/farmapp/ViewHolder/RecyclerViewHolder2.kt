package com.app.farm.farmapp.ViewHolder

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.farm.farmapp.Activity.MainNavActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_chat.view.*


class RecyclerViewHolder2(view: View) : RecyclerView.ViewHolder(view) {

    // Listener
    interface ItemClickListener2 {
        fun onItemClick2(view: View, position: Int)

    }

    fun setData(hobby: MainNavActivity.Post?, pos: Int, activity: AppCompatActivity) {

        println("Successful MyViewHoldersetData $position")
        hobby?.let {
            itemView.tv_user_name.text = hobby.name
            itemView.tv_last_chat.text = hobby.caption
           Glide.with(activity).load(hobby.post_pic).into(itemView.iv_user_photo)


        }?: run {

        }
    }
}