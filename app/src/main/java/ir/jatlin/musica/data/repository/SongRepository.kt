package ir.jatlin.musica.data.repository

import android.util.Log
import ir.jatlin.musica.data.model.Song
import ir.jatlin.musica.data.source.song.SongDataSource
import javax.inject.Inject

class SongRepository @Inject constructor(
    private val dataSource: SongDataSource
) : Repository {

    init {
        Log.d("song_repository", "respository: Created... ")
    }

    override suspend fun getAllSongs(): List<Song> {
        return dataSource.fetchSongList()
    }
}