package ir.jatlin.musica.ui.songs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.jatlin.musica.common.Resource
import ir.jatlin.musica.data.model.Song
import ir.jatlin.musica.ui.SongCard
import ir.jatlin.musica.ui.theme.songs.SongsViewModel
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
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(
            items = songsList,
            key = { _, song -> song.uri.toString() }
        ) { index, song ->

            SongCard(
                song = song,
                hasDivider = index < songsList.lastIndex,
                onCLicked = navigateToPlayer
            )
        }

    }
}


@Preview
@Composable
fun SongsPreview() {

}