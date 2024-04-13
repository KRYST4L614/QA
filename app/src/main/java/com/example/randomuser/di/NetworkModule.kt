package com.example.randomuser.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.randomuser.BuildConfig
import com.example.randomuser.data.database.RandomUsersDatabase
import com.example.randomuser.data.database.models.UserEntity
import com.example.randomuser.data.network.api.RandomUserApi
import com.example.randomuser.data.network.mediator.RandomUserRemoteMediator
import com.example.randomuser.data.repositories.RepositoryRandomUserApiImpl
import com.example.randomuser.domain.repositories.RepositoryRandomUserApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(includes = [DatabaseModule::class])
class NetworkModule {
    @ExperimentalPagingApi
    @Provides
    fun providesPager(db: RandomUsersDatabase, api: RandomUserApi): Pager<Int, UserEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = BuildConfig.PAGER_PAGE_SIZE,
                prefetchDistance = BuildConfig.PAGER_PREFETCH_DISTANCE
            ), BuildConfig.PAGER_INITIAL_KEY, remoteMediator = RandomUserRemoteMediator(
                db,
                api
            ), pagingSourceFactory = {
                db.dao.pagingSource()
            }
        )
    }

    @Provides
    fun providesRetrofit(converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideMoshiConverterFactory(): Converter.Factory {
        return MoshiConverterFactory.create(
            Moshi
                .Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        )
    }

    @Provides
    fun provideRepositoryRandomUserApi(): RepositoryRandomUserApi {
        return RepositoryRandomUserApiImpl.get()
    }

    @Provides
    fun provideRandomUserApi(retrofit: Retrofit): RandomUserApi {
        return retrofit.create(RandomUserApi::class.java)
    }
}