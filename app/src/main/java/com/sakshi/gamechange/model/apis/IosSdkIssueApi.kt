package com.sakshi.gamechange.model.apis

import com.sakshi.gamechange.arch.Urls
import com.sakshi.gamechange.model.schema.IssueDetail
import com.sakshi.gamechange.model.schema.RepoIssue
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IosSdkIssueApi {
    @GET(Urls.ISSUES_URL)
    fun getIssuesListAsync(): Call<List<RepoIssue>>

    @GET(Urls.ISSUE_DETAIL_URL)
    fun getIssuesCommentListAsync(@Path("comment_id") id: String): Call<List<IssueDetail>>
}