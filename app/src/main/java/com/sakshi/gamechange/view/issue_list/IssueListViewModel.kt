package com.sakshi.gamechange.view.issue_list

import androidx.lifecycle.ViewModel
import com.sakshi.gamechange.model.repository.IosSdkRepository

class IssueListViewModel : ViewModel() {

    fun setRepository(iosSdkRepository: IosSdkRepository) {
        iosSdkRepository.getIssues()
    }

    fun cancelApiCall(iosSdkRepository: IosSdkRepository) {
        iosSdkRepository.cancelApiCalls()
    }
}
