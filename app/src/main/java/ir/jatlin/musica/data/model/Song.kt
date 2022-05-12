package ir.jatlin.musica.data.model

import android.net.Uri


data class Song(
    val uri: Uri,
    val name: String,
    val artist: String,
    val album: Album,
    val duration: Int,
)
