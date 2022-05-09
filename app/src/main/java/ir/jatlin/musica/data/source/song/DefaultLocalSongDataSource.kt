package ir.jatlin.musica.data.source.song

import ir.jatlin.musica.common.media.MediaProvider
import ir.jatlin.musica.data.model.Song

class DefaultLocalSongDataSource(
    private val songProvider: MediaProvider<List<Song>>
) : LocalSongDataSource {


    override suspend fun fetchSongList(): List<Song> {
        return songProvider.get()
    }
}