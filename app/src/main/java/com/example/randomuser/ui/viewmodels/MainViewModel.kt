package com.example.randomuser.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.randomuser.data.database.models.UserEntity
import com.example.randomuser.data.repositories.db.RepositoryRandomUserDb
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repositoryDb: RepositoryRandomUserDb,
    pager: Pager<Int, UserEntity>
) : ViewModel() {

    val pagerLiveData: LiveData<PagingData<UserEntity>> = pager.liveData.cachedIn(viewModelScope)

    fun getUserById(primaryId: Int): LiveData<UserEntity> = repositoryDb.getUserById(primaryId)

    class Factory @Inject constructor(
        private val repositoryDb: RepositoryRandomUserDb,
        private val pager: Pager<Int, UserEntity>
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repositoryDb, pager) as T
        }
    }
}