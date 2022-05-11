package ir.jatlin.musica.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.jatlin.musica.common.media.MediaProvider
import ir.jatlin.musica.data.model.Song
import ir.jatlin.musica.data.source.song.DefaultSongDataSource
import ir.jatlin.musica.data.source.song.SongDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideSongDataSource(
        songProvider: @JvmSuppressWildcards MediaProvider<List<Song>>
    ): SongDataSource = DefaultSongDataSource(songProvider = songProvider)
}