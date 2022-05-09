package ir.jatlin.musica.data.source.song

import ir.jatlin.musica.data.model.Song

interface LocalSongDataSource {

    suspend fun fetchSongList(): List<Song>
}