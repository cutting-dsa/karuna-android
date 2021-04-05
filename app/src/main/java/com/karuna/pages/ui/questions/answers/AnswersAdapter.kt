package com.karuna.pages.ui.questions.answers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karuna.pages.R
import com.karuna.pages.data.entities.Answer
import com.karuna.pages.utils.OnItemClickListener
import kotlinx.android.synthetic.main.answer_list_item.view.*

class AnswersAdapter(private val answers: List<Answer>) :
    RecyclerView.Adapter<AnswersAdapter.MyViewHolder>() {
    var listener: OnItemClickListener<Answer>? = null

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun populateViewWithAnswer(answer: Answer) {
            with(itemView) {
                txtName.text = "${answer.user.firstName} ${answer.user.lastName}"
                txtAnswer.text = answer.name
                txtTime.text = answer.created_at
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.answer_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val answer = answers[position]
        holder.populateViewWithAnswer(answer)
    }

    override fun getItemCount(): Int {
        return answers.size
    }
}