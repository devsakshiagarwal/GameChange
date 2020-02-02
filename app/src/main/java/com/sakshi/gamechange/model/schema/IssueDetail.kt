package com.sakshi.gamechange.model.schema

import com.squareup.moshi.Json

data class IssueDetail(
    @field:Json(name = "body") val body: String = "",
    @field:Json(name = "user") val user: User = User()
)

data class User(@field:Json(name = "login") val login: String = "")