package ir.jatlin.musica.ui.theme.songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.jatlin.musica.common.Resource
import ir.jatlin.musica.data.model.Song
import ir.jatlin.musica.domain.GetAllSongsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SongsViewModel(
    private val getAllSongs: GetAllSongsUseCase
) : ViewModel() {

    private val _songs = MutableStateFlow<Resource<List<Song>>>(Resource.success(emptyList()))
    val songs = _songs.asStateFlow()

    init {
        fetchSongs()
    }

    private fun fetchSongs() = viewModelScope.launch {
        getAllSongs(Unit).collect {
            _songs.value = it
        }
    }

}