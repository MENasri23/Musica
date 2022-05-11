package ir.jatlin.musica.data.model

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap


data class Song(
    val uri: Uri,
    val name: String,
    val artist: String,
    val album: String,
    val duration: Int,
    val photo: ImageBitmap? = null
)
