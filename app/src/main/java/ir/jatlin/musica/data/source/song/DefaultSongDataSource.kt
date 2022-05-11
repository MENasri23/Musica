package ir.jatlin.musica.data.source.song

import ir.jatlin.musica.common.media.MediaProvider
import ir.jatlin.musica.data.model.Song
import javax.inject.Inject

class DefaultSongDataSource @Inject constructor(
    private val songProvider: @JvmSuppressWildcards MediaProvider<List<Song>>
) : SongDataSource {


    override suspend fun fetchSongList(): List<Song> {
        return songProvider.get()
    }
}