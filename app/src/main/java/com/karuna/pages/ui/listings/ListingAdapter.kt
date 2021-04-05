package com.karuna.pages.ui.listings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karuna.pages.R
import com.karuna.pages.data.entities.Listing
import com.karuna.pages.utils.OnItemClickListener
import kotlinx.android.synthetic.main.listing_list_item.view.*

class ListingAdapter(private val listings: List<Listing>) :
    RecyclerView.Adapter<ListingAdapter.MyViewHolder>() {
    var listener: OnItemClickListener<Listing>? = null

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun populateViewWithListing(listing: Listing, listener: OnItemClickListener<Listing>?) {
            with(itemView) {
                setOnClickListener { listener?.invoke(listing) }
                txtName.text = listing.listingname
                txtOwner.text = "${listing.listinguser.firstName} ${listing.listinguser.lastName}"
                txtCategory.text = listing.category.name
                txtAddress.text = listing.address
                ratingBar.rating = listing.averageRating.toFloat()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.listing_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listing = listings[position]
        holder.populateViewWithListing(listing, listener)
    }

    override fun getItemCount(): Int {
        return listings.size
    }
}