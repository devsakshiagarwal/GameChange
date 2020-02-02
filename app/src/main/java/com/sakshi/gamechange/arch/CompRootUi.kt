package com.sakshi.gamechange.arch

import androidx.appcompat.app.AppCompatActivity

class CompRootUi(
    private val compRoot: CompRoot,
    private val activityContext: AppCompatActivity
) {
    fun getRouter() = Router(activityContext, activityContext.supportFragmentManager)
}