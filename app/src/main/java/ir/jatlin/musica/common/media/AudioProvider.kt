package ir.jatlin.musica.common.media

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.provider.MediaStore
import androidx.compose.ui.graphics.asImageBitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.jatlin.musica.common.map
import ir.jatlin.musica.data.model.Song
import java.lang.IllegalArgumentException
import javax.inject.Inject

class AudioProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : MediaProvider<List<@kotlin.jvm.JvmSuppressWildcards Song>> {

    private val projection = arrayOf(
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.DURATION
    )

    private val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"

    private val cursor = context.applicationContext.contentResolver?.query(
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        projection,
        selection,
        null,
        null
    )


    @Throws()
    override fun get(): List<Song> {
        if (cursor == null) {
            throw  IllegalArgumentException()
        }

        val columnsIndex = getSongColumnsIndex(cursor = cursor)
        val songs = cursor.map { parseSong(it, columnsIndex) }

        cursor.close()

        return songs
    }

    private fun getSongColumnsIndex(cursor: Cursor): ColumnsIndex {
        val idColumn = cursor.getColumnIndexOrThrow(projection[0])
        val nameColumn = cursor.getColumnIndexOrThrow(projection[1])
        val artistColumn = cursor.getColumnIndexOrThrow(projection[2])
        val durationColumn = cursor.getColumnIndexOrThrow(projection[3])

        return ColumnsIndex(idColumn, nameColumn, artistColumn, durationColumn)

    }

    private fun parseSong(c: Cursor, indices: ColumnsIndex): Song? = c.run {
        val uri = ContentUris.withAppendedId(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            c.getLong(indices.id)
        )
        val name = getString(indices.name)
        val artist = getString(indices.artist)
        val duration = getInt(indices.duration)

        val mmRetriever = MediaMetadataRetriever()
        val bitmapOptions = BitmapFactory.Options()

        try {
            mmRetriever.setDataSource(context.applicationContext, uri)
            val photoByteArray = mmRetriever.embeddedPicture

            val photo = photoByteArray?.let { photo ->
                BitmapFactory.decodeByteArray(
                    photo,
                    0,
                    photo.size,
                    bitmapOptions
                ).asImageBitmap()
            }

            Song(
                uri = uri,
                name = name,
                artist = artist,
                duration = duration,
                photo = photo
            )
        } catch (e: RuntimeException) {
            null
        }
    }


    class ColumnsIndex(
        val id: Int,
        val name: Int,
        val artist: Int,
        val duration: Int
    )
}