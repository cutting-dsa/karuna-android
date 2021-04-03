package com.karuna.pages.ui.mylistings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karuna.pages.R
import com.karuna.pages.data.entities.Listing

class MyListingAdapter(private val listings: List<Listing>) :
    RecyclerView.Adapter<MyListingAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtName: TextView = view.findViewById(R.id.txtName)
        var txtCategory: TextView = view.findViewById(R.id.txtCategory)
        var txtAddress: TextView = view.findViewById(R.id.txtAddress)
        var ratingBar: RatingBar = view.findViewById(R.id.ratingBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.listing_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listing = listings[position]
        holder.txtName.text = listing.listingname
        holder.txtCategory.text = listing.category.name
        holder.txtAddress.text = listing.address
//        holder.ratingBar.setRating = listing.address
    }

    override fun getItemCount(): Int {
        return listings.size
    }
}