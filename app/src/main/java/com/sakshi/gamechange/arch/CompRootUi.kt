package com.sakshi.gamechange.arch

import androidx.fragment.app.FragmentActivity
import com.sakshi.gamechange.model.database.AppDatabase
import com.sakshi.gamechange.model.repository.IosSdkRepository

class CompRootUi(
    private val compRoot: CompRoot,
    private val activityContext: FragmentActivity
) {

    fun getRouter() = Router(activityContext.supportFragmentManager)

    fun getSdkIssueRepository() = IosSdkRepository(compRoot.getIsoSdkApi(), getDb())

    fun getDb() = AppDatabase.invoke(activityContext.applicationContext)
}