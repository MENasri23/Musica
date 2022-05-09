package ir.jatlin.musica.data.repository

import ir.jatlin.musica.common.Resource
import ir.jatlin.musica.data.model.Song
import ir.jatlin.musica.data.source.song.SongDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SongRepository(
    private val dataSource: SongDataSource
) : Repository {

    override fun getAllSongs(): Flow<Resource<List<Song>>> {
        return flow {
            emit(Resource.loading())
            try {
                emit(Resource.success(dataSource.fetchSongList()))
            } catch(t: Throwable) {
                emit(Resource.error(t))
            }
        }
    }
}