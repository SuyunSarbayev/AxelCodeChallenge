package com.example.impl

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.api.data.UserDetails
import com.example.api.repository.FeatureRepository
import com.example.impl.domain.UserDetailFeatureViewModel
import com.example.ui.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.wheneverBlocking
import java.net.ConnectException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class UserDetailFeatureViewModelTest {
    private val savedStateHandle: SavedStateHandle = Mockito.mock()
    private val featureRepository: FeatureRepository = Mockito.mock()

    private val userDetailViewModel by lazy {
        UserDetailFeatureViewModel(savedStateHandle, featureRepository)
    }

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        givenUsername("John")
    }

    @Test
    fun `GIVEN user detail request WHEN successful response THEN check if item is the same`() {
        runTest {
            givenUserDetail("John")

            userDetailViewModel.initiateRequestUserDetail()

            userDetailViewModel.userDetailState.test {
                val item = awaitItem()
                assert(item is UiState.Success)
                assert((item as UiState.Success).data == USER_DETAIL)
            }
        }
    }

    @Test
    fun `GIVEN user detail failure request and database item is empty WHEN received response THEN check for Error state`() {
        runBlocking {
            wheneverBlocking { featureRepository.userDetails("John") }.thenReturn(
                flow {
                    throw ConnectException("No internet connection")
                },
            )
            userDetailViewModel.initiateRequestUserDetail()

            userDetailViewModel.userDetailState.test {
                val item = awaitItem()
                assert(item is UiState.Error)
            }
        }
    }

    fun givenUsername(username: String) {
        Mockito.`when`(savedStateHandle.get<String>("username")).thenReturn(username)
    }

    fun givenUserDetail(username: String) {
        wheneverBlocking {
            featureRepository.userDetails(username)
        }.thenReturn(flow { emit(USER_DETAIL) })
    }

    companion object {
        val USER_DETAIL = UserDetails(
            login = "John",
            id = 1,
        )
    }
}
