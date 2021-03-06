package io.github.agimaulana.monity.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.agimaulana.monity.dao.DailyExpenseDao
import io.github.agimaulana.monity.dao.DailyRationDao
import io.github.agimaulana.monity.data.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Singleton
    @Provides
    fun provideDailyRationDao(appDatabase: AppDatabase): DailyRationDao = appDatabase.dailyRationDao

    @Singleton
    @Provides
    fun provideDailyExpenseDao(appDatabase: AppDatabase): DailyExpenseDao = appDatabase.dailyExpenseDao

}