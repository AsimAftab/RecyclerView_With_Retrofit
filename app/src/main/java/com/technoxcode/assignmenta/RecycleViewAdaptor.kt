package com.technoxcode.assignmenta

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecycleViewAdaptor(private val context: Context, private val products:List<Product>) : RecyclerView.Adapter<RecycleViewAdaptor.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView = itemView.findViewById(R.id.rv_Image)
        val tvName: TextView = itemView.findViewById(R.id.rv_tv)
        val rvDes: TextView=itemView.findViewById(R.id.rv_des)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        Glide.with(context).load(product.thumbnail).into(holder.rvImage)
        holder.tvName.text = product.title
        holder.rvDes.text=product.description
    }
}
