package com.giaquino.android.cropped.di

import com.giaquino.android.cropped.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideSharedRepository(repository: SharedRepositoryImpl) : SharedRepository

    @Binds
    @Singleton
    abstract fun provideTopicRepository(repository: TopicRepositoryImpl) : TopicRepository

    @Binds
    @Singleton
    abstract fun provideUserRepository(repository: UserRepositoryImpl) : UserRepository
}