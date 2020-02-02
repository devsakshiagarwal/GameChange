package com.sakshi.gamechange.view.issue_detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sakshi.gamechange.R
import com.sakshi.gamechange.model.schema.IssueDetailDb
import kotlinx.android.synthetic.main.list_comments.view.*

class IssueDetailAdapter(private val list: List<IssueDetailDb>, private val context: Context) :
    RecyclerView.Adapter<IssueDetailAdapter.RepoCommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoCommentsViewHolder {
        return RepoCommentsViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_comments, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoCommentsViewHolder, position: Int) {
        val repoComments: IssueDetailDb = list[position]
        holder.tvTitle.text = repoComments.user
        holder.tvBody.text = repoComments.body
    }

    override fun getItemCount(): Int = list.size

    class RepoCommentsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: AppCompatTextView = view.tv_name
        val tvBody: AppCompatTextView = view.tv_comment
    }
}