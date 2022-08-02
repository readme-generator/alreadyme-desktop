package dev.yjyoon.alreadyme.data.di

import dagger.Module
import dagger.Provides
import dev.yjyoon.alreadyme.data.repository.ReadmeRepository
import io.ktor.client.*
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideReadmeRepository(client: HttpClient) = ReadmeRepository(client)
}
