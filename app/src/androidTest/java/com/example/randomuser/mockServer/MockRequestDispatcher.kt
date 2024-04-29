package com.example.randomuser.mockServer

import android.content.res.Resources
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
            .setBody(getLocalJsonBody(foundRequest.localPath))
    }

    fun addMockRequests(vararg mockRequests: MockRequest) {
        requestsList.addAll(mockRequests)
    }

    private fun getLocalJsonBody(relativePath: String): String {
        val resource = this.javaClass.classLoader
            ?.getResourceAsStream(relativePath)
            ?: throw Resources.NotFoundException(
                "Unable to find '$relativePath'"
            )

        return String(resource.readBytes())
    }
}