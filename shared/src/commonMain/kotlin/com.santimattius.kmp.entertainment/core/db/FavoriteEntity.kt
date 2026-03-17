package com.santimattius.kmp.entertainment.core.db

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "favorite")
class FavoriteEntity(
    @PrimaryKey @ColumnInfo(name = "id") val resourceId: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "type") val type: String,
)