package it.unina.dietiestates

import android.content.Context
import android.text.Editable
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import it.unina.dietiestates.controller.auth.SignUpController
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.kotlin.anyOrNull
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [30], manifest = Config.NONE)
class SignUpControllerTest {

    private lateinit var context: Context
    private lateinit var signUpController: SignUpController

    private lateinit var nomeEditText: EditText
    private lateinit var cognomeEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext() // Use Robolectric's test context
        signUpController = spy(SignUpController(context)) // Spy to track method calls

        nomeEditText = mock(EditText::class.java)
        cognomeEditText = mock(EditText::class.java)
        emailEditText = mock(EditText::class.java)
        passwordEditText = mock(EditText::class.java)

        // Mock Editable responses
        mockEditText(nomeEditText, "Mario")
        mockEditText(cognomeEditText, "Rossi")
        mockEditText(emailEditText, "mario.rossi@example.com")
        mockEditText(passwordEditText, "password123")
    }

    private fun mockEditText(editText: EditText, text: String) {
        val editable = mock(Editable::class.java)
        `when`(editable.toString()).thenReturn(text)
        `when`(editText.text).thenReturn(editable)
    }

    @Test
    fun handleSignUp_ValidInput_Test() {
        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)

        verify(signUpController, times(1)).signUp(any())
        verify(signUpController, never()).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_EmptyName_Test() {
        mockEditText(nomeEditText, "")

        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)

        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_WhiteSpaceName_Test() {
        mockEditText(nomeEditText, " ")

        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)

        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_EmptySurname_Test() {
        mockEditText(cognomeEditText, "")

        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)

        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_WhiteSpaceSurname_Test() {
        mockEditText(cognomeEditText, "\n")

        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)

        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_EmptyEmail_Test() {
        mockEditText(emailEditText, "")

        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)

        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_WhiteSpaceEmail_Test() {
        mockEditText(emailEditText, "\t")

        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)

        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_EmptyPassword_Test() {
        mockEditText(passwordEditText, "")

        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)

        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_WhiteSpacePassword_Test() {
        mockEditText(passwordEditText, " ")

        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)

        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }
}
