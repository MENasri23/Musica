package ir.jatlin.musica.common.media

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.jatlin.musica.common.map
import ir.jatlin.musica.data.model.Album
import ir.jatlin.musica.data.model.Song
import javax.inject.Inject

class AudioProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : MediaProvider<List<@kotlin.jvm.JvmSuppressWildcards Song>> {

    private val projection = arrayOf(
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.ALBUM,
        MediaStore.Audio.Media.ALBUM_ID,
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
    override suspend fun get(): List<Song> {
        if (cursor == null) {
            throw  IllegalArgumentException()
        }

        return cursor.use { c ->
            val columnsIndex = getSongColumnsIndex(c)
            c.map { parseSong(it, columnsIndex) }
        }
    }

    private fun getSongColumnsIndex(cursor: Cursor): ColumnsIndex {
        val idColumn = cursor.getColumnIndexOrThrow(projection[0])
        val songNameColumn = cursor.getColumnIndexOrThrow(projection[1])
        val artistColumn = cursor.getColumnIndexOrThrow(projection[2])
        val albumNameColumn = cursor.getColumnIndexOrThrow(projection[3])
        val albumIdColumn = cursor.getColumnIndexOrThrow(projection[4])
        val durationColumn = cursor.getColumnIndexOrThrow(projection[5])

        return ColumnsIndex(
            id = idColumn,
            SongName = songNameColumn,
            artist = artistColumn,
            albumName = albumNameColumn,
            albumId = durationColumn,
            duration = albumIdColumn
        )

    }

    private fun parseSong(c: Cursor, indices: ColumnsIndex): Song =
        c.run {
            val albumId = getLong(indices.albumId)
            val albumName = getString(indices.albumName)
            val albumImageUrl = getAlbumImageUrl(albumId)
            val album = Album(albumId, albumName, albumImageUrl)

            val name = getString(indices.SongName)
            val artist = getString(indices.artist)
            val songUri = getSongUri(getLong(indices.id))
            val duration = getInt(indices.duration)

            Song(
                uri = songUri,
                name = name,
                artist = artist,
                album = album,
                duration = duration
            )
        }

    private fun getSongUri(songId: Long) =
        ContentUris.withAppendedId(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, songId
        )

    private fun getAlbumImageUrl(albumId: Long) =
        ContentUris.withAppendedId(Uri.parse(ALBUM_ART_URI), albumId)

    companion object {
        private const val ALBUM_ART_URI = "content://media/external/audio/albumart"
    }


    class ColumnsIndex(
        val id: Int,
        val SongName: Int,
        val artist: Int,
        val albumName: Int,
        val albumId: Int,
        val duration: Int
    )
}