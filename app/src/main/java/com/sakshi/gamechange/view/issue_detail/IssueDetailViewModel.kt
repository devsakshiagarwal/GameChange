package com.sakshi.gamechange.view.issue_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sakshi.gamechange.model.repository.IosSdkRepository
import com.sakshi.gamechange.model.schema.IssueDetailDb

class IssueDetailViewModel : ViewModel(), IosSdkRepository.IosSdkCommentsListener {

    var liveDataCommentsList = MutableLiveData<List<IssueDetailDb>>()

    fun setRepository(iosSdkRepository: IosSdkRepository, commentId: String) {
        iosSdkRepository.setCommentListListener(this)
        iosSdkRepository.getComments(commentId)
    }

    fun cancelApiCall(iosSdkRepository: IosSdkRepository) {
        iosSdkRepository.cancelApiCalls()
    }

    override fun onIssueCommentListSuccess(issuesList: List<IssueDetailDb>) {
        liveDataCommentsList.value = issuesList
    }

    override fun onFailure() {
        liveDataCommentsList.value = null
    }
}
