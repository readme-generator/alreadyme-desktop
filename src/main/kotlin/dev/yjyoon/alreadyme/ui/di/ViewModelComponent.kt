package dev.yjyoon.alreadyme.ui.di

import dagger.Component
import dev.yjyoon.alreadyme.data.di.DataModule
import dev.yjyoon.alreadyme.data.di.RepositoryModule
import dev.yjyoon.alreadyme.ui.main.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface ViewModelComponent {
    fun callMainViewModel(): MainViewModel
}
