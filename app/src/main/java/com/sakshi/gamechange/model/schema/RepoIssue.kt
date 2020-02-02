package com.sakshi.gamechange.model.schema

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class RepoIssue(
    @field:Json(name = "id") @PrimaryKey val id: Long = 0,
    @field:Json(name = "title") @ColumnInfo val title: String = "",
    @field:Json(name = "body") @ColumnInfo val body: String = "",
    @field:Json(name = "comments_url") @ColumnInfo val commentsUrl: String = ""
)