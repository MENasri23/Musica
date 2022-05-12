package ir.jatlin.musica.data.model

import android.net.Uri

data class Album(
    val id: Long,
    val name: String,
    val imageUrl: Uri
)