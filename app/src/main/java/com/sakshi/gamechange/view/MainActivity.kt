package com.sakshi.gamechange.view

import android.os.Bundle
import com.sakshi.gamechange.R
import com.sakshi.gamechange.arch.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compRoot()!!.getRouter().toIssueListFragment()
    }
}
