package ir.jatlin.musica.data.repository

import ir.jatlin.musica.data.model.Song

interface Repository {

     suspend fun getAllSongs(): List<Song>
}