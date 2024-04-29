package com.example.randomuser.mockServer

data class MockRequest(
    val remotePath: String,
    val localPath: String,
    val requestCode: Int,
)