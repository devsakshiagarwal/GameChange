package com.sakshi.gamechange.view.issue_list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.sakshi.gamechange.R
import com.sakshi.gamechange.arch.BaseFragment
import com.sakshi.gamechange.model.repository.IosSdkRepository
import kotlinx.android.synthetic.main.issue_list_fragment.*

class IssueListFragment : BaseFragment(), IssueListAdapter.OnIssueItemClickListener {

    private lateinit var iosSdkRepository: IosSdkRepository
    private lateinit var viewModel: IssueListViewModel

    companion object {
        fun newInstance() = IssueListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.issue_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iosSdkRepository = compRoot()!!.getSdkIssueRepository()
        viewModel = ViewModelProviders.of(this).get(IssueListViewModel::class.java)
        viewModel.setRepository(iosSdkRepository)
        startObserving()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelApiCall(iosSdkRepository)
    }

    private fun startObserving() {
        compRoot()!!.getDb().issueDao().getAll().observe(viewLifecycleOwner, Observer {
            rv_issue_list.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter =
                    IssueListAdapter(it, activity!!.applicationContext, this@IssueListFragment)

            }
        })
    }

    override fun onItemClickListener(commentUrl: String) {
        compRoot()!!.getRouter().toIssueDetailFragment(commentUrl)
    }
}
