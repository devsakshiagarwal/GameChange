package com.sakshi.gamechange.model.repository

import android.os.AsyncTask
import com.sakshi.gamechange.model.apis.IosSdkIssueApi
import com.sakshi.gamechange.model.database.AppDatabase
import com.sakshi.gamechange.model.database.RepoIssuesDao
import com.sakshi.gamechange.model.schema.IssueDetail
import com.sakshi.gamechange.model.schema.IssueDetailDb
import com.sakshi.gamechange.model.schema.RepoIssue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IosSdkRepository(private val iosSdkIssueApi: IosSdkIssueApi, private val db: AppDatabase) {

    private val issuesCall = iosSdkIssueApi.getIssuesListAsync()
    private lateinit var commentsCall: Call<List<IssueDetail>>
    private lateinit var issueCommentListListener: IosSdkCommentsListener


    fun setCommentListListener(issueCommentListListener: IosSdkCommentsListener) {
        this.issueCommentListListener = issueCommentListListener
    }

    fun getIssues() {
        issuesCall.enqueue(object : Callback<List<RepoIssue>> {
            override fun onResponse(
                call: Call<List<RepoIssue>>,
                response: Response<List<RepoIssue>>
            ) {
                if (response.code() == 200) {
                    for (issue in response.body()!!) {
                        InsertIssue(db.issueDao()).execute(issue)
                    }
                }
            }

            override fun onFailure(call: Call<List<RepoIssue>>, t: Throwable) {

            }
        })
    }

    fun getComments(commentId: String) {
        commentsCall = iosSdkIssueApi.getIssuesCommentListAsync(commentId)
        commentsCall.enqueue(object : Callback<List<IssueDetail>> {
            override fun onResponse(
                call: Call<List<IssueDetail>>,
                response: Response<List<IssueDetail>>
            ) {
                if (response.code() == 200 && response.body()!!.isNotEmpty()) {
                    issueCommentListListener.onIssueCommentListSuccess(getComments(response.body()!!))
                } else {
                    issueCommentListListener.onFailure()
                }
            }

            override fun onFailure(call: Call<List<IssueDetail>>, t: Throwable) {
                issueCommentListListener.onFailure()
            }
        })
    }

    fun cancelApiCalls() {
        issuesCall.cancel()
        commentsCall.cancel()
    }

    private fun getComments(commentsList: List<IssueDetail>): List<IssueDetailDb> {
        val commentList: MutableList<IssueDetailDb> = mutableListOf()
        for (comments in commentsList) {
            commentList
                .add(IssueDetailDb(comments.id, comments.body, comments.user.login))
        }
        return commentList
    }

    private class InsertIssue(val issueDao: RepoIssuesDao) : AsyncTask<RepoIssue, Unit, Unit>() {

        override fun doInBackground(vararg repoIssues: RepoIssue) {
            issueDao.insertAll(*repoIssues)
        }
    }

    interface IosSdkCommentsListener {
        fun onIssueCommentListSuccess(issuesList: List<IssueDetailDb>)
        fun onFailure()
    }

}