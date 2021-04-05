package com.karuna.pages.ui.reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karuna.pages.R
import com.karuna.pages.data.entities.Review
import kotlinx.android.synthetic.main.review_list_item.view.*

class ReviewAdapter(private val reviews: List<Review>) :
    RecyclerView.Adapter<ReviewAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun populateViewWithAnswer(review: Review) {
            with(itemView) {
                txtName.text = "${review.reviewUser.firstName} ${review.reviewUser.lastName}"
                txtComment.text = review.comment
                txtListing.text = review.listing.listingname
                ratingBar.rating = review.rating.toFloat()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.review_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = reviews[position]
        holder.populateViewWithAnswer(review)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }
}