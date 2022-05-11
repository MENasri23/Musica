package ir.jatlin.musica.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.jatlin.musica.data.repository.Repository
import ir.jatlin.musica.data.repository.SongRepository
import ir.jatlin.musica.data.source.song.SongDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSongRepository(
        songDataSource: SongDataSource
    ): Repository = SongRepository(songDataSource)
}