package com.alex.sid.shante.diceroller.di

import com.alex.sid.shante.diceroller.data.GameRepositoryImpl
import com.alex.sid.shante.diceroller.domain.repositories.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideGameRepository(): GameRepository {
        return GameRepositoryImpl()
    }
}