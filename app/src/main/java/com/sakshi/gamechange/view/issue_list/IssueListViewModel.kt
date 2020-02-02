package com.sakshi.gamechange.view.issue_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sakshi.gamechange.model.repository.IosSdkRepository
import com.sakshi.gamechange.model.schema.RepoIssue

class IssueListViewModel : ViewModel(), IosSdkRepository.IosSdkIssueListener {

    var liveDataIssuesList = MutableLiveData<List<RepoIssue>>()

    fun setRepository(iosSdkRepository: IosSdkRepository) {
        iosSdkRepository.setIssueListListener(this)
        iosSdkRepository.getIssues()
    }

    fun cancelApiCall(iosSdkRepository: IosSdkRepository) {
        iosSdkRepository.cancelApiCalls()
    }

    override fun onIssueListSuccess(issuesList: List<RepoIssue>) {
        liveDataIssuesList.value = issuesList
    }

    override fun onFailure() {
        liveDataIssuesList.value = null
    }
}
