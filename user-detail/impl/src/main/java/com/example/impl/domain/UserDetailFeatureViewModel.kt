package com.example.impl.domain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.data.UserDetails
import com.example.api.repository.FeatureRepository
import com.example.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailFeatureViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val featureRepository: FeatureRepository,
) : ViewModel() {

    private val _userDetailState = MutableStateFlow<UiState<UserDetails>>(UiState.Loading)

    val userDetailState: StateFlow<UiState<UserDetails?>> = _userDetailState

    private val username = savedStateHandle.get<String>("username")

    init {
        initiateRequestUserDetail()
    }

    fun initiateRequestUserDetail() {
        viewModelScope.launch {
            featureRepository.userDetails(username!!)
                .onStart { _userDetailState.value = UiState.Loading }
                .catch { _userDetailState.value = UiState.Error(it) }
                .collect { _userDetailState.value = UiState.Success(it) }
        }
    }
}
