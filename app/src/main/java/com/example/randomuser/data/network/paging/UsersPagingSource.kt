package com.example.randomuser.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.randomuser.data.network.models.UserData
import com.example.randomuser.data.repositories.network.RepositoryRandomUserApi

class UsersPagingSource(
    private val repository: RepositoryRandomUserApi
) : PagingSource<Int, UserData>() {
    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val closePage = state.closestPageToPosition(anchorPosition) ?: return null
        return closePage.prevKey?.minus(1) ?: closePage.nextKey?.plus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {
        val page = params.key ?: 1
        val pageSize = params.loadSize.coerceAtMost(10)
        val response = repository.fetchUsersPage(page)
        val newPrevKey = if (page == 1) null else page - 1
        val newNextKey =
            if ((response.body()?.userItems?.size ?: 0) < pageSize) null else page + 1
        return LoadResult.Page(
            response.body()?.userItems?: emptyList(), newPrevKey,
            newNextKey
        )
    }
}