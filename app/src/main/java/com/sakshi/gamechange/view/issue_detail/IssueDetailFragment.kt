package com.sakshi.gamechange.view.issue_detail

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
import kotlinx.android.synthetic.main.issue_detail_fragment.*

class IssueDetailFragment : BaseFragment() {

    private lateinit var iosSdkRepository: IosSdkRepository
    private lateinit var viewModel: IssueDetailViewModel

    companion object {
        const val COMMENT_ID = "comment_id"
        fun newInstance(commentId: String): IssueDetailFragment {
            val issueDetailFragment = IssueDetailFragment()
            val args = Bundle()
            args.putString(COMMENT_ID, commentId)
            issueDetailFragment.arguments = args
            return issueDetailFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.issue_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tool_bar.title = getString(R.string.comments_for) + arguments!!.getString(COMMENT_ID, "")
        iosSdkRepository = compRoot()!!.getSdkIssueRepository()
        viewModel = ViewModelProviders.of(this).get(IssueDetailViewModel::class.java)
        viewModel.setRepository(iosSdkRepository, arguments!!.getString(COMMENT_ID, ""))
        startObserving()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelApiCall(iosSdkRepository)
    }

    private fun startObserving() {
        viewModel.liveDataCommentsList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                tv_no_data.visibility = View.GONE
                rv_comment_list.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter =
                        IssueDetailAdapter(it, activity!!.applicationContext)

                }
            } else {
                tv_no_data.visibility = View.VISIBLE
            }
        })
    }
}
