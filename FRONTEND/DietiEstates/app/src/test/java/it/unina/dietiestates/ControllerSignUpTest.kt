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
    fun handleSignUpValidInputTest() {
        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)

        verify(signUpController).signUp(any()) // Ensure signUp was called
        verify(signUpController, never()).showToast(anyOrNull(), anyOrNull()) // Ensure showToast was NOT called
    }

    @Test
    fun handleSignUpInvalidNameTest() {
        // Simulate invalid input
        mockEditText(nomeEditText, "")

        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)

        verify(signUpController, never()).signUp(any()) // Ensure signUp was NOT called
        verify(signUpController).showToast(anyOrNull(), anyOrNull()) // Ensure showToast was called
    }

    @Test
    fun handleSignUpInvalidSurnameTest() {
        // Simulate invalid input
        mockEditText(cognomeEditText, "")

        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)

        verify(signUpController, never()).signUp(any()) // Ensure signUp was NOT called
        verify(signUpController).showToast(anyOrNull(), anyOrNull()) // Ensure showToast was called
    }

    @Test
    fun handleSignUpInvalidEmailTest() {
        // Simulate invalid input
        mockEditText(emailEditText, "")

        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)

        verify(signUpController, never()).signUp(any()) // Ensure signUp was NOT called
        verify(signUpController).showToast(anyOrNull(), anyOrNull()) // Ensure showToast was called
    }

    @Test
    fun handleSignUpInvalidPasswordTest() {
        // Simulate invalid input
        mockEditText(passwordEditText, "")

        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)

        verify(signUpController, never()).signUp(any()) // Ensure signUp was NOT called
        verify(signUpController).showToast(anyOrNull(), anyOrNull()) // Ensure showToast was called
    }
}
