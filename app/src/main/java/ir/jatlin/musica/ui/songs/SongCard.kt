package ir.jatlin.musica.ui.songs

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
    hasDivider: Boolean,
    modifier: Modifier = Modifier,
    onCLicked: (songUrl: String) -> Unit = {}
) {

    ConstraintLayout(
        modifier = modifier
            .clickable { onCLicked(song.uri.toString()) }
    ) {

        val (image, songContent, overflow, divider) = createRefs()

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
                .clip(Shapes.small)
                .background(Color.DarkGray)
                .constrainAs(image) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)

                }
        )

        Column(
            modifier = Modifier
                .constrainAs(songContent) {
                    centerVerticallyTo(image)
                    linkTo(
                        start = image.end,
                        end = overflow.start,
                        startMargin = 16.dp,
                        endMargin = 4.dp,
                    )
                    width = Dimension.fillToConstraints
                }
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

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(overflow) {
                end.linkTo(parent.end, margin = 8.dp)
                centerVerticallyTo(songContent)
            }
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(id = R.string.more)
            )
        }

        if (hasDivider) {
            Divider(
                modifier = Modifier.constrainAs(divider) {
                    top.linkTo(image.bottom, margin = 8.dp)
                    start.linkTo(songContent.start)
                    end.linkTo(parent.end, margin = 4.dp)

                    width = Dimension.fillToConstraints

                }
            )
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
                    name = "Just a Heads Up!What's up Hello How ",
                    artist = "Maher Zain",
                    album = Album(
                        id = 1,
                        name = "Learning English",
                        imageUrl = Uri.parse("https://i.scdn.co/image/ab67616d00001e026094c28a79dc47837fd269d9")
                    ),
                    duration = 3600,
                ),
                modifier = Modifier.padding(4.dp),
                hasDivider = true
            )

        }
    }
}