package com.mandeep.practise_projects.project1.Paging_for_retrofit

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mandeep.practise_projects.R
import com.mandeep.practise_projects.project1.MainActivity
import com.mandeep.practise_projects.project1.Retrofit.DataClasses.Hit

class MyPagingDataAdapter(val context: Context, val activity: Activity):PagingDataAdapter<Hit,MyPagingDataAdapter.MyViewHolder>(diffUtilItemCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

     val view = LayoutInflater.from(context).inflate(R.layout.item,parent,false)
        Log.d("3r93jf","fidnfe")
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val hit = getItem(position)

        holder.idTextView.text = hit?.id.toString()

        Glide.with(context).load(hit?.largeImageURL).error(R.drawable.ic_launcher_background).centerCrop().encodeQuality(50).transition(DrawableTransitionOptions.withCrossFade()).into(holder.imageView)

        holder.itemView.setOnClickListener {

            hit?.let {
                (activity as MainActivity).showDialog(it) }

        }

    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val imageView:ImageView = itemView.findViewById(R.id.wallImagevIew)
        val idTextView: TextView =  itemView.findViewById(R.id.idTextView)
    }

    object diffUtilItemCallback : DiffUtil.ItemCallback<Hit>(){
        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
           return oldItem == newItem
        }
    }
}