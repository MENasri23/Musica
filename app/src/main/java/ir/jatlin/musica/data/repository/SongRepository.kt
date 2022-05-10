package ir.jatlin.musica.data.repository

import ir.jatlin.musica.data.model.Song
import ir.jatlin.musica.data.source.song.SongDataSource

class SongRepository(
    private val dataSource: SongDataSource
) : Repository {


    override suspend fun getAllSongs(): List<Song> {
        return dataSource.fetchSongList()
    }
}