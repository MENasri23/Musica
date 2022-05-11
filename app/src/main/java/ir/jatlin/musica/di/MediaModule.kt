package ir.jatlin.musica.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.jatlin.musica.common.media.AudioProvider
import ir.jatlin.musica.common.media.MediaProvider
import ir.jatlin.musica.data.model.Song
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface MediaModule {

    @Binds
    fun bindSongMediaProvider(
        songProvider: AudioProvider
    ): MediaProvider<List<Song>>

}

/*
@Module
@InstallIn(SingletonComponent::class)
class MediaModule {

    @Provides
    fun bindSongMediaProvider(
        songProvider: AudioProvider
    ): MediaProvider<List<Song>> = songProvider

}*/
