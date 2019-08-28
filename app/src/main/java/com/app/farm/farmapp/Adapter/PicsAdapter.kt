package com.app.farm.farmapp.Adapter

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.farm.farmapp.Activity.MainNavActivity
import com.app.farm.farmapp.R
import com.app.farm.farmapp.ViewHolder.RecyclerViewHolder

internal class PicsAdapter constructor(private val activity: AppCompatActivity, private val itemClickListener: RecyclerViewHolder.ItemClickListener, private val messagesadapter: MainNavActivity.MessagesFeed) : RecyclerView.Adapter<RecyclerViewHolder>() {


    private var mRecyclerView : RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        if (recyclerView != null) {
            super.onAttachedToRecyclerView(recyclerView)
        }
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        if (recyclerView != null) {
            super.onDetachedFromRecyclerView(recyclerView)
        }
        mRecyclerView = null

    }

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
		val view = LayoutInflater.from(activity).inflate(R.layout.list_item_chat2, parent, false)
        view.setOnClickListener { view ->
            mRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }

        return RecyclerViewHolder(view)
	}

	override fun getItemCount(): Int {
		return messagesadapter.post.count()
	}

	override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
		val hobby = messagesadapter.post.get(position)
		holder.setData(hobby, position,activity)
	}

}
