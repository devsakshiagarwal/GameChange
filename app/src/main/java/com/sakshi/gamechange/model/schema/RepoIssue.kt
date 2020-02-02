package com.sakshi.gamechange.model.schema

import com.squareup.moshi.Json

data class RepoIssue(
    @field:Json(name = "title") val title: String = "",
    @field:Json(name = "body") val body: String = "",
    @field:Json(name = "comments_url") val commentsUrl: String = ""
)