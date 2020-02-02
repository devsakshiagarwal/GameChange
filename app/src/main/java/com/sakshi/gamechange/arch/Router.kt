package com.sakshi.gamechange.arch

import androidx.fragment.app.FragmentManager
import com.sakshi.gamechange.R
import com.sakshi.gamechange.view.issue_detail.IssueDetailFragment
import com.sakshi.gamechange.view.issue_list.IssueListFragment

class Router(
    private val fragmentManager: FragmentManager
) {

    fun toIssueListFragment() {
        val issueListFragment = IssueListFragment.newInstance()
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, issueListFragment)
        transaction.commit()
    }

    fun toIssueDetailFragment(commentId: String) {
        val issueDetailFragment = IssueDetailFragment.newInstance(commentId)
        val transaction = fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, issueDetailFragment)
            addToBackStack("ISSUE_DETAIL_FRAGMENT")
        }
        transaction.commit()
    }

}