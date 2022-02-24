package io.github.loshine.v2compose.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.loshine.v2compose.data.repository.V2exRepository
import io.github.loshine.v2compose.data.repository.impl.V2exDataRepository

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsV2exRepository(v2exDataRepository: V2exDataRepository): V2exRepository
}