package com.getmemes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getmemes.R
import com.getmemes.data.model.Meme
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val list: ArrayList<Meme>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Meme) {
            Glide.with(itemView.imageView.context)
                .load(item.url)
                .into(itemView.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(list[position])

    fun addData(list: List<Meme>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}