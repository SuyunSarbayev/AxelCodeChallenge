package com.example.impl

import androidx.paging.PagingData
import com.example.api.data.User
import com.example.api.repository.FeatureRepository
import com.example.impl.domain.FeatureViewModel
import com.example.impl.repository.FeaturePagerRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FeatureViewModelTest {

    private val usersRepository: FeatureRepository = mockk()
    private val usersPagerRepository: FeaturePagerRepository = mockk()

    private val viewModel by lazy { FeatureViewModel(usersRepository, usersPagerRepository) }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `GIVEN one image request WHEN successful response THEN check if there is one result`() {
        runTest {
            val pagingData = PagingData.from(listOf<User>())
            every { usersPagerRepository.initiateSearchUsers("John") } returns flowOf(pagingData)

            launch {
                viewModel.setSearchQuery("John")
            }

            val result = viewModel.usersPaging.take(1).toList()

            Assert.assertEquals(result.size, 1)
        }
    }

    @Test
    fun `GIVEN two users request WHEN there is two successful responses THEN check if there is exactly two results`() {
        runTest(StandardTestDispatcher()) {
            val pagingData1 = PagingData.from(listOf<User>(mockk()))
            val pagingData2 = PagingData.from(listOf<User>(mockk()))
            every {
                usersPagerRepository.initiateSearchUsers("Alan")
            } returns flowOf(pagingData1)
            every {
                usersPagerRepository.initiateSearchUsers("Drew")
            } returns flowOf(pagingData2)

            launch {
                viewModel.setSearchQuery("Alan")
            }

            val resultFruits = viewModel.usersPaging.take(1).toList()

            Assert.assertEquals(resultFruits.size, 1)

            launch {
                viewModel.setSearchQuery("Drew")
            }

            val resultCars = viewModel.usersPaging.take(1).toList()

            Assert.assertEquals(resultCars.size, 1)
        }
    }
}
