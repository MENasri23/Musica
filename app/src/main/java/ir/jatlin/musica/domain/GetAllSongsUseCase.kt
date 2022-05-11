package ir.jatlin.musica.domain

import ir.jatlin.musica.data.model.Song
import ir.jatlin.musica.data.repository.SongRepository
import javax.inject.Inject

class GetAllSongsUseCase @Inject constructor(
    private val songRepository: SongRepository
) : FlowUseCase<Unit, List<Song>>() {

    override suspend fun execute(params: Unit): List<Song> {
        return songRepository.getAllSongs()
    }
}