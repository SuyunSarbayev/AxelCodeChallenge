package com.example.impl.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.api.data.User
import com.example.impl.repository.FeaturePagerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FeatureViewModel @Inject constructor(
    private val featurePagerRepository: FeaturePagerRepository,
) : ViewModel() {

    private val _search = MutableStateFlow("")

    val search = _search.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = "",
        )

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val usersPaging: Flow<PagingData<User>> = search
        .debounce { 800L }
        .flatMapLatest { featurePagerRepository.initiateSearchUsers(it) }
        .cachedIn(viewModelScope)

    fun setSearchQuery(query: String) {
        _search.value = query
    }
}
