package com.neox.restoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neox.restoapp.R
import com.neox.restoapp.model.Value
import com.neox.restoapp.service.helper.RecyclerViewItemClickSupport
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_cafe.view.*

class ListCafeAdapter(private val listItemCafe: List<Value>) : RecyclerView.Adapter<ListCafeAdapter.ViewHolder>() {

    private lateinit var recyclerViewItemClickSupport: RecyclerViewItemClickSupport

    fun setOnItemClickCallback(recyclerItemClickSupport: RecyclerViewItemClickSupport) {
        this.recyclerViewItemClickSupport = recyclerItemClickSupport
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_cafe, parent, false))
    }

    override fun getItemCount(): Int = listItemCafe.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataCafe(listItemCafe[position])
        holder.itemView.setOnClickListener {
            recyclerViewItemClickSupport.onItemClick(listItemCafe[position])
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindDataCafe(dataCafe: Value) {
            with(itemView) {

                Picasso.get().load(dataCafe.spacePhoto)
                    .placeholder(R.drawable.ic_load)
                    .error(R.drawable.ic_image_error)
                    .into(imageViewCafe)

                textViewCafeName.text = dataCafe.spaceName
            }
        }
    }
}