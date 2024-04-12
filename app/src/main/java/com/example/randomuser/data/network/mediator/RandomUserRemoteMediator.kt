package com.example.randomuser.data.network.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.randomuser.data.database.RandomUsersDatabase
import com.example.randomuser.data.database.models.UserEntity
import com.example.randomuser.data.mappers.toUserEntity
import com.example.randomuser.data.network.api.RandomUserApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class RandomUserRemoteMediator(
    private val randomUsersDb: RandomUsersDatabase,
    private val randomUserApi: RandomUserApi
) : RemoteMediator<Int, UserEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        lastItem.primaryId / state.config.pageSize
                    }
                }
            }
            val users = randomUserApi.getUsersPage(
                loadKey,
                state.config.pageSize
            )
            randomUsersDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    randomUsersDb.dao.clearAll()
                }
                val response = users.execute()
                val usersList = response.body()?.userItems
                randomUsersDb.dao.upsertAll(usersList!!.map {
                    it.toUserEntity()
                })
                MediatorResult.Success(
                    endOfPaginationReached = usersList.isEmpty()
                )
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}