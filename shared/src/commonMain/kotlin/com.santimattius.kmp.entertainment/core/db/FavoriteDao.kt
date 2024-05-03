package com.santimattius.kmp.entertainment.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getAllAsFlow(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite WHERE id=:id")
    suspend fun findById(id: Int): FavoriteEntity?

    @Insert
    suspend fun insert(item: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE  id=:id")
    suspend fun deleteById(id: Int)

}