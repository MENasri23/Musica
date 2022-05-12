package ir.jatlin.musica.common

import android.database.Cursor

suspend fun <T> Cursor.map(mapper: suspend (Cursor) -> T?): List<T> {
    return mutableListOf<T>().apply {
        while (moveToNext()) {
            mapper(this@map)?.let { add(it) }
        }
    }
}