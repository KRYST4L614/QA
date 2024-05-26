package com.example.randomuser.mockServer

import android.content.res.Resources
import com.example.randomuser.util.getLocalJsonBody
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

object MockRequestDispatcher : Dispatcher() {
    private val requestsList = mutableListOf<MockRequest>()

    override fun dispatch(request: RecordedRequest): MockResponse {
        val foundRequest = requestsList.find {
            it.remotePath == request.path
        } ?: throw IllegalStateException()
        return MockResponse().setResponseCode(foundRequest.requestCode)
            .setBody(getLocalJsonBody<MockRequestDispatcher>(foundRequest.localPath))
    }

    fun addMockRequests(vararg mockRequests: MockRequest) {
        requestsList.addAll(mockRequests)
    }
}