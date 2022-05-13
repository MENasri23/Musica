package ir.jatlin.musica.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ir.jatlin.musica.R
import ir.jatlin.musica.data.model.Album
import ir.jatlin.musica.data.model.Song
import ir.jatlin.musica.ui.theme.MusicaTheme
import ir.jatlin.musica.ui.theme.Shapes

@Composable
fun SongCard(
    song: Song,
    modifier: Modifier = Modifier
) {

    Card(modifier = modifier
        .wrapContentHeight()
        .clickable { /* TODO */ }
        .alpha(0.87f)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clip(Shapes.small)
                .background(Color.LightGray) // TODO: Remove this line after composing layout
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(song.uri)
                    .crossfade(true)
                    .placeholder(R.drawable.loading_animation)
                    .build(),
                error = painterResource(id = R.drawable.ic_launcher_foreground),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.music_size))
                    .background(Color.DarkGray)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = song.name,
                    style = MaterialTheme.typography.h6,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Text(text = song.artist)
            }

        }
    }

}


@Preview(name = "Song Card")
@Preview(name = "Song Card Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SongCardPreview() {
    MusicaTheme {
        Surface {
            SongCard(
                song = Song(
                    uri = "imageUril".toUri(),
                    name = "Just a Heads Up!What's up",
                    artist = "Maher Zhene",
                    album = Album(
                        id = 1,
                        name = "Learning English",
                        imageUrl = Uri.parse("https://i.scdn.co/image/ab67616d00001e026094c28a79dc47837fd269d9")
                    ),
                    duration = 3600,
                ),
                modifier = Modifier.padding(4.dp)
            )

        }
    }
}