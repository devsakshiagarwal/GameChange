package com.sakshi.gamechange.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sakshi.gamechange.model.schema.IssueDetailDb
import com.sakshi.gamechange.model.schema.RepoIssue

@Database(entities = [RepoIssue::class, IssueDetailDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun issueDao(): RepoIssuesDao
    abstract fun commentDao(): IssueDetailsDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "repo-list.db")
            .build()
    }
}