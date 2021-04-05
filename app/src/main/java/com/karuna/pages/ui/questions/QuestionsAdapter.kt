package com.karuna.pages.ui.questions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karuna.pages.R
import com.karuna.pages.data.entities.Question
import com.karuna.pages.utils.OnItemClickListener
import kotlinx.android.synthetic.main.question_list_item.view.*

class QuestionsAdapter(private val questions: List<Question>) :
    RecyclerView.Adapter<QuestionsAdapter.MyViewHolder>() {
    var listener: OnItemClickListener<Question>? = null

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun populateViewWithQuestion(question: Question, listener: OnItemClickListener<Question>?) {
            with(itemView) {
                setOnClickListener { listener?.invoke(question) }
                txtCategory.text = question.category.name
                txtQuestion.text = question.name
                txtTime.text = question.created_at
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val question = questions[position]
        holder.populateViewWithQuestion(question, listener)
    }

    override fun getItemCount(): Int {
        return questions.size
    }
}