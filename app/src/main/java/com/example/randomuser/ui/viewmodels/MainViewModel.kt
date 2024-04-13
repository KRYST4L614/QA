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
import com.example.randomuser.domain.repositories.RepositoryRandomUserDb
import javax.inject.Inject

class MainViewModel @Inject constructor(
    pager: Pager<Int, UserEntity>,
    private val repositoryDb: RepositoryRandomUserDb
) : ViewModel() {

    val pagerLiveData: LiveData<PagingData<UserEntity>> = pager.liveData.cachedIn(viewModelScope)
    fun getUserById(primaryId: Int): LiveData<UserEntity> = repositoryDb.getUserById(primaryId)

    class Factory @Inject constructor(
        private val pager: Pager<Int, UserEntity>,
        private val repositoryDb: RepositoryRandomUserDb,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(pager, repositoryDb) as T
        }
    }
}