package ir.jatlin.musica.common.media

interface MediaProvider<out T> {

    suspend fun get(): T
}


