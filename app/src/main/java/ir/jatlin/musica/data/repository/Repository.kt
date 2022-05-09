package ir.jatlin.musica.data.repository

import ir.jatlin.musica.common.Resource
import ir.jatlin.musica.data.model.Song
import kotlinx.coroutines.flow.Flow

interface Repository {

     fun getAllSongs(): Flow<Resource<List<Song>>>
}