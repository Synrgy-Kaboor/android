package com.synrgy.kaboor.authentication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.synrgy.domain.auth.AuthUseCase
import com.synrgy.domain.auth.model.request.EmailParam
import com.synrgy.domain.user.UserUseCase
import com.synrgy.kaboor.utils.CoroutinesTestRule
import com.synrgy.kaboor.utils.DummyDataTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by wahid on 1/30/2024.
 * Github github.com/wahidabd.
 */


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AuthViewModelTest {

    @get:Rule
    var instanceExecutor = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var authUseCase: AuthUseCase

    @Mock
    private lateinit var userUseCase: UserUseCase

    @Mock
    private lateinit var viewModel: AuthViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = AuthViewModel(authUseCase, userUseCase)
    }


    @Test
    fun `test check email - success`() = runTest {
        val expectedResponse = DummyDataTest.checkEmailResponse()
        val request = EmailParam(DummyDataTest.validEmail)

        `when`(authUseCase.checkEmail(request)).thenReturn(flowOf(DummyDataTest.flowGenericSuccess()))
        viewModel.checkEmail(request)

        Assert.assertEquals(expectedResponse.value, viewModel.generic.value)
    }

    @Test
    fun `test check email - error`() = runTest {
        val expectedResponse = DummyDataTest.checkEmailResponse()
        val request = EmailParam(DummyDataTest.emptyString)

        `when`(authUseCase.checkEmail(request)).thenReturn(flowOf(DummyDataTest.flowGenericError()))
        viewModel.checkEmail(request)

        Assert.assertNotEquals(expectedResponse.value, viewModel.generic.value)
    }

    @Test
    fun `test login - success`() = runTest {
        val expectedResponse = DummyDataTest.loginResponse()
        val request = DummyDataTest.loginParam

        `when`(authUseCase.login(request)).thenReturn(flowOf(DummyDataTest.flowLogin(true)))
        viewModel.login(request)

        Assert.assertEquals(expectedResponse.value, viewModel.jwt.value)
    }

    @Test
    fun `test login - error`() = runTest {
        val expectedResponse = DummyDataTest.loginResponse()
        val request = DummyDataTest.loginParam

        `when`(authUseCase.login(request)).thenReturn(flowOf(DummyDataTest.flowLogin(false)))
        viewModel.login(request)

        Assert.assertNotEquals(expectedResponse.value, viewModel.jwt.value)
    }
}