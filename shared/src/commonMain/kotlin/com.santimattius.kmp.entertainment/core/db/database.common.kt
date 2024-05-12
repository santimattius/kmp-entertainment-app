package com.santimattius.kmp.entertainment.core.db

import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO


expect class RoomFactory {

    fun create(): RoomDatabase.Builder<TMDBDataBase>
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<TMDBDataBase>,
    queryDispatcher: CoroutineDispatcher = Dispatchers.IO
): TMDBDataBase {
    return builder
        .setQueryCoroutineContext(queryDispatcher)
        .build()
}
