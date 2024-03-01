package com.example.randomuser.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.room.Room
import com.example.randomuser.RandomUserApplication
import com.example.randomuser.data.database.RandomUsersDatabase
import com.example.randomuser.data.database.models.UserEntity
import com.example.randomuser.data.network.api.RandomUserApi
import com.example.randomuser.data.network.models.UserData
import com.example.randomuser.data.network.paging.RandomUserRemoteMediator
import com.example.randomuser.data.network.paging.UsersPagingSource
import com.example.randomuser.data.repositories.db.RepositoryRandomUserDb
import com.example.randomuser.data.repositories.db.RepositoryRandomUserDbImpl
import com.example.randomuser.data.repositories.network.RepositoryRandomUserApi
import com.example.randomuser.data.repositories.network.RepositoryRandomUserApiImpl
import com.example.randomuser.ui.fragments.MainFragment
import com.example.randomuser.ui.fragments.UserInfoFragment
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val PAGER_PAGE_SIZE = 15
private const val PAGER_PREFETCH_DISTANCE = 5
private const val PAGER_INITIAL_KEY = 1
private const val BASE_URL = "https://randomuser.me/"
private const val DATABASE_NAME = "RandomUsersDb"

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(randomUserApplication: RandomUserApplication)
    fun inject(mainFragment: MainFragment)
    fun inject(userInfoFragment: UserInfoFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent

    }

}

@Module(includes = [NetworkModule::class])
class AppModule

@Module(includes = [Database::class])
class NetworkModule {
    @ExperimentalPagingApi
    @Provides
    fun providesPager(db: RandomUsersDatabase, api: RandomUserApi): Pager<Int, UserEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGER_PAGE_SIZE,
                prefetchDistance = PAGER_PREFETCH_DISTANCE
            ), PAGER_INITIAL_KEY, remoteMediator = RandomUserRemoteMediator(
                db,
                api
            ), pagingSourceFactory = {
                db.dao.pagingSource()
            }
        )
    }

    @Provides
    fun providePagingSource(repository: RepositoryRandomUserApi): PagingSource<Int, UserData> {
        return UsersPagingSource(repository)
    }

    @Provides
    fun providesRetrofit(converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
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

@Module
class Database {
    @Provides
    fun provideDatabase(context: Context): RandomUsersDatabase {
        return Room.databaseBuilder(context, RandomUsersDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    fun provideRepositoryRandomUserDb(): RepositoryRandomUserDb {
        return RepositoryRandomUserDbImpl.get()
    }
}