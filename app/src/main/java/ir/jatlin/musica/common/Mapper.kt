package ir.jatlin.musica.common

import android.database.Cursor

fun <T> Cursor.map(mapper: (Cursor) -> T?): List<T> {
    return mutableListOf<T>().apply {
        while (moveToNext()) {
            mapper(this@map)?.let { add(it) }
        }
    }
}