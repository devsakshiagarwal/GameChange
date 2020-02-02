package com.sakshi.gamechange.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sakshi.gamechange.model.schema.IssueDetailDb

@Dao
interface IssueDetailsDao {
    @Query("SELECT * FROM issuedetaildb")
    fun getAll(): LiveData<List<IssueDetailDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg comment: IssueDetailDb)

}