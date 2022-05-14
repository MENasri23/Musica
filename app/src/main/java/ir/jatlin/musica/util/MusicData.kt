package ir.jatlin.musica.util

import androidx.core.net.toUri
import ir.jatlin.musica.data.model.Album
import ir.jatlin.musica.data.model.Song

object MusicData {

    val song1 = Song(
        uri = "https://picsum.photos/id/1/5616/3744".toUri(),
        name = "Just a Heads Up!What's up",
        artist = "Alejandro Escamilla",
        album = Album(
            id = 1,
            name = "Learning English",
            imageUrl = "https://i.scdn.co/image/ab67616d00001e026094c28a79dc47837fd269d9".toUri()
        ),
        duration = 3600,
    )


    val song2 = Song(
        uri = "https://picsum.photos/id/10/2500/1667".toUri(),
        name = "Just a Heads Up!What's up",
        artist = "Paul Jarvis",
        album = Album(
            id = 1,
            name = "Learning English",
            imageUrl = "https://picsum.photos/id/10/2500/1667".toUri()
        ),
        duration = 2000,
    )


    val song3 = Song(
        uri = "https://picsum.photos/id/100/2500/1656".toUri(),
        name = "Just a Heads Up!What's up",
        artist = "Tina Rataj",
        album = Album(
            id = 1,
            name = "Learning English",
            imageUrl = "https://picsum.photos/id/100/2500/1656".toUri()
        ),
        duration = 2750,
    )

    val song4 = Song(
        uri = "https://picsum.photos/id/1000/5626/3635".toUri(),
        name = "Just a Heads Up!",
        artist = "Lukas Budimaier",
        album = Album(
            id = 1,
            name = "Learning English",
            imageUrl = "https://picsum.photos/id/1000/5626/3635".toUri()
        ),
        duration = 4200,
    )


    val song5 = Song(
        uri = "https://picsum.photos/id/1012/3973/2639".toUri(),
        name = "Just ",
        artist = "Scott Webb",
        album = Album(
            id = 1,
            name = "Go Ahead",
            imageUrl = "https://picsum.photos/id/1012/3973/2639".toUri()
        ),
        duration = 4200,
    )

    val song6 = Song(
        uri = "https://picsum.photos/id/1013/4256/2832".toUri(),
        name = "Just a Heads Up!What's upfwfw wfwf",
        artist = "Cayton Heath",
        album = Album(
            id = 1,
            name = "Learning English",
            imageUrl = "https://picsum.photos/id/1013/4256/2832".toUri()
        ),
        duration = 4200,
    )


    val song7 = Song(
        uri = "https://picsum.photos/id/1016/3844/2563".toUri(),
        name = "Just a Heads Up!What's up",
        artist = "Philippe Wuyts",
        album = Album(
            id = 1,
            name = "Learning English",
            imageUrl = "https://picsum.photos/id/1016/3844/2563".toUri()
        ),
        duration = 4200,
    )

    val songs = listOf(
        song1,
        song2,
        song3,
        song4,
        song5,
        song6,
        song7,
    )


}