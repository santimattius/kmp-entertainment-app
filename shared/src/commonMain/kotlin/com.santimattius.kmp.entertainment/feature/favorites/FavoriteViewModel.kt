package com.santimattius.kmp.entertainment.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santimattius.kmp.entertainment.core.data.repositories.FavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoriteViewModel(
    private val repository: FavoriteRepository,
) : ViewModel() {
    val state = repository.all.map { values ->
        values.map { item ->
            FavoriteUiModel(
                id = item.resourceId,
                title = item.title,
                description = item.overview,
                imageUrl = item.imageUrl,
                type = item.type
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    fun onFavoriteDelete(item: FavoriteUiModel) {
        repository.remove(item.id)
    }
}
