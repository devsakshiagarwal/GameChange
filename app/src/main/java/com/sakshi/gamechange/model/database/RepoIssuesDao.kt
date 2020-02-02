package com.sakshi.gamechange.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sakshi.gamechange.model.schema.RepoIssue

@Dao
interface RepoIssuesDao {
    @Query("SELECT * FROM repoissue")
    fun getAll(): LiveData<List<RepoIssue>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg issue: RepoIssue)
}