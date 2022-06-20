package com.mandeep.practise_projects.project1.paging_for_room

import android.content.Context
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
import com.mandeep.practise_projects.project1.MVVM.MyViewModel
import com.mandeep.practise_projects.project1.MainActivity
import com.mandeep.practise_projects.project1.Room.EntityItem

class MyPagingDataAdpater2(val context: Context):PagingDataAdapter<EntityItem,MyPagingDataAdpater2.MyViewHolder2>(diffItemCallBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
       val view = LayoutInflater.from(context).inflate(R.layout.item,parent,false)
        return MyViewHolder2(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {

        val entityItem = getItem(position)

        holder.idTextView.text = entityItem?.id.toString()

        Glide.with(context).load(entityItem?.largeImageURL).error(R.drawable.ic_launcher_background).centerCrop().encodeQuality(50).transition(
            DrawableTransitionOptions.withCrossFade()).into(holder.imageView)

    }
    class MyViewHolder2 (itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imageView: ImageView = itemView.findViewById(R.id.wallImagevIew)
        val idTextView: TextView =  itemView.findViewById(R.id.idTextView)
    }

    object diffItemCallBack :DiffUtil.ItemCallback<EntityItem>(){
        override fun areContentsTheSame(oldItem: EntityItem, newItem: EntityItem): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: EntityItem, newItem: EntityItem): Boolean {
            return oldItem == newItem
        }
    }
}