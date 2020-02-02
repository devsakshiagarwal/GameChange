package com.sakshi.gamechange.model.schema

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IssueDetailDb(
    @PrimaryKey val id: Long = 0,
    @ColumnInfo val body: String = "",
    @ColumnInfo val user: String = ""
)