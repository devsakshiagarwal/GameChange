package com.sakshi.gamechange.model.repository

import com.sakshi.gamechange.model.apis.IosSdkIssueApi
import com.sakshi.gamechange.model.schema.IssueDetail
import com.sakshi.gamechange.model.schema.RepoIssue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IosSdkRepository(private val iosSdkIssueApi: IosSdkIssueApi) {

    private val issuesCall = iosSdkIssueApi.getIssuesListAsync()
    private lateinit var commentsCall: Call<List<IssueDetail>>
    private lateinit var issueListListener: IosSdkIssueListener
    private lateinit var issueCommentListListener: IosSdkCommentsListener

    fun setIssueListListener(issueListListener: IosSdkIssueListener) {
        this.issueListListener = issueListListener
    }

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
                    issueListListener.onIssueListSuccess(response.body()!!)
                } else {
                    issueListListener.onFailure()
                }
            }

            override fun onFailure(call: Call<List<RepoIssue>>, t: Throwable) {
                issueListListener.onFailure()
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
                    issueCommentListListener.onIssueCommentListSuccess(response.body()!!)
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

    interface IosSdkIssueListener {
        fun onIssueListSuccess(issuesList: List<RepoIssue>)
        fun onFailure()
    }

    interface IosSdkCommentsListener {
        fun onIssueCommentListSuccess(issuesList: List<IssueDetail>)
        fun onFailure()
    }
}