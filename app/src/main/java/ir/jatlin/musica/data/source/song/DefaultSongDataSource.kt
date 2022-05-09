package ir.jatlin.musica.data.source.song

import ir.jatlin.musica.common.media.MediaProvider
import ir.jatlin.musica.data.model.Song

class DefaultSongDataSource(
    private val songProvider: MediaProvider<List<Song>>
) : SongDataSource {


    override suspend fun fetchSongList(): List<Song> {
        return songProvider.get()
    }
}