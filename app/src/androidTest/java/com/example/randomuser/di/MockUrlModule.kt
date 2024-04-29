package com.example.randomuser.di

import dagger.Module

@Module
class MockUrlModule : NetworkModule() {
    override fun baseUrl() = "http://localhost:8080/"
}