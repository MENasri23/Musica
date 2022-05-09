package ir.jatlin.musica.common.media

interface MediaProvider<out T> {

    fun get(): T
}


