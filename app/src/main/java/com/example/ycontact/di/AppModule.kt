package com.example.ycontact.di

import com.example.ycontact.data.ContactRepository
import com.example.ycontact.data.ContactRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun contactRepository() : ContactRepository{
        return ContactRepositoryImpl()
    }
}