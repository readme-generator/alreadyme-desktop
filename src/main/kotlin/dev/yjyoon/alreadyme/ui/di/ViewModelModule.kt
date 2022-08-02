package dev.yjyoon.alreadyme.ui.di

import dagger.Module
import dagger.Provides
import dev.yjyoon.alreadyme.data.repository.ReadmeRepository
import dev.yjyoon.alreadyme.ui.main.MainViewModel
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideMainViewModel(repository: ReadmeRepository) = MainViewModel(repository)
}
