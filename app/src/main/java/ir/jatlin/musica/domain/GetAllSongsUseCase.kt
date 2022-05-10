package ir.jatlin.musica.domain

import ir.jatlin.musica.data.model.Song
import ir.jatlin.musica.data.repository.SongRepository

class GetAllSongsUseCase(
    private val songRepository: SongRepository
) : FlowUserCase<Unit, List<Song>>() {

    override suspend fun execute(params: Unit): List<Song> {
        return songRepository.getAllSongs()
    }
}