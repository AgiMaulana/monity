package io.github.agimaulana.monity.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.agimaulana.monity.dao.DailyRationDao
import io.github.agimaulana.monity.repository.DailyRationRepository

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideDailyRationRepository(dailyRationDao: DailyRationDao): DailyRationRepository {
        return DailyRationRepository(dailyRationDao)
    }

}