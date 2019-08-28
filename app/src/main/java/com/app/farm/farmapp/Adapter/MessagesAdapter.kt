package com.app.farm.farmapp.Adapter

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.farm.farmapp.*
import com.app.farm.farmapp.Activity.MainNavActivity
import com.app.farm.farmapp.ViewHolder.RecyclerViewHolder2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.list_item_chat.view.*


internal class MessagesAdapter constructor(private val activity: AppCompatActivity, private val itemClickListener2: RecyclerViewHolder2.ItemClickListener2, private val messagesadapter: MainNavActivity.MessagesFeed) : RecyclerView.Adapter<RecyclerViewHolder2>() {


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

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder2 {
		val view = LayoutInflater.from(activity).inflate(R.layout.list_item_chat, parent, false)
		view.setOnClickListener { view ->
			mRecyclerView?.let {
				itemClickListener2.onItemClick2(view, it.getChildAdapterPosition(view))
			}
		}

		return RecyclerViewHolder2(view)
	}

	override fun getItemCount(): Int {
		return messagesadapter.post.count()
	}

	override fun onBindViewHolder(holder: RecyclerViewHolder2, position: Int) {
		val hobby = messagesadapter.post.get(position)
		holder.setData(hobby, position,activity)
	}


}
