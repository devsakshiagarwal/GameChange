package com.sakshi.gamechange.view.issue_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sakshi.gamechange.R
import com.sakshi.gamechange.model.schema.RepoIssue
import kotlinx.android.synthetic.main.list_issues.view.*

class IssueListAdapter(
    private val list: List<RepoIssue>,
    private val context: Context,
    private val onItemClickListener: OnIssueItemClickListener
) :
    RecyclerView.Adapter<IssueListAdapter.RepoIssueViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoIssueViewHolder {
        return RepoIssueViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_issues, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoIssueViewHolder, position: Int) {
        val repoIssue: RepoIssue = list[position]
        holder.tvTitle.text = repoIssue.title
        holder.tvBody.text = getBody(repoIssue.body)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClickListener(getCommentId(repoIssue.commentsUrl))
        }
    }

    private fun getBody(body: String): String {
        return if (body.length <= 140) {
            body
        } else {
            body.dropLast(body.length - 140)
        }
    }

    private fun getCommentId(commentUrl: String) : String {
        return commentUrl.substringAfter("issues/").substringBefore("/comments")
    }

    override fun getItemCount(): Int = list.size

    class RepoIssueViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: AppCompatTextView = view.tv_title
        val tvBody: AppCompatTextView = view.tv_body
    }

    interface OnIssueItemClickListener {
        fun onItemClickListener(commentUrl: String)
    }
}