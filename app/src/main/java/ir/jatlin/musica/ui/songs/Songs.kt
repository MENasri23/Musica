package ir.jatlin.musica.ui.songs

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ir.jatlin.musica.common.Resource
import ir.jatlin.musica.data.model.Song
import ir.jatlin.musica.ui.theme.MusicaTheme
import ir.jatlin.musica.ui.theme.songs.SongsViewModel
import ir.jatlin.musica.util.MusicData
import timber.log.Timber

@Composable
fun Songs(
    songsViewModel: SongsViewModel,
    navigateToPlayer: (songUri: String) -> Unit
) {
    Timber.d("songs stateful called again...")

    val songs by songsViewModel.songs.collectAsState()
    when (songs) {
        is Resource.Loading -> {}
        is Resource.Success -> {
            val songsList = songs.data
            SongsContent(
                songsList = songsList ?: emptyList(),
                navigateToPlayer = navigateToPlayer
            )
        }
        is Resource.Error -> {}
    }


}

@Composable
private fun SongsContent(
    songsList: List<Song>,
    modifier: Modifier = Modifier,
    navigateToPlayer: (songUri: String) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(
            items = songsList,
            key = { _, song -> song.uri.toString() }
        ) { index, song ->

            SongCard(
                song = song,
                hasDivider = index < songsList.lastIndex,
                onCLicked = navigateToPlayer,
                modifier = Modifier.fillParentMaxWidth()
            )
        }

    }
}


@Preview()
@Composable
fun SongsPreview() {
    MusicaTheme {
        SongsContent(songsList = MusicData.songs, navigateToPlayer = { })
    }
}