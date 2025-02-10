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
        context = ApplicationProvider.getApplicationContext()
        signUpController = spy(SignUpController(context))

        //Arrange
        nomeEditText = mock(EditText::class.java)
        cognomeEditText = mock(EditText::class.java)
        emailEditText = mock(EditText::class.java)
        passwordEditText = mock(EditText::class.java)

        mockEditText(nomeEditText, "Mario")
        mockEditText(cognomeEditText, "Rossi")
        mockEditText(emailEditText, "mario.rossi@example.com")
        mockEditText(passwordEditText, "pAssword123!")
    }

    private fun mockEditText(editText: EditText, text: String) {
        val editable = mock(Editable::class.java)
        `when`(editable.toString()).thenReturn(text)
        `when`(editText.text).thenReturn(editable)
    }

    @Test
    fun handleSignUp_ValidInput_Test() { //ACE3+, BCE3+, CCE3+, DCE3+
        //Act
        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)
        //Assert
        verify(signUpController, times(1)).signUp(any())
        verify(signUpController, never()).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_EmptyName_Test() { //ACE1-, BCE3+, CCE3+, DCE3+
        mockEditText(nomeEditText, "") //Arrange
        //Act
        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)
        //Assert
        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_WhiteSpaceName_Test() { //ACE2-, BCE3+, CCE3+, DCE3+
        mockEditText(nomeEditText, " ") //Arrange
        //Act
        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)
        //Assert
        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_EmptySurname_Test() { //ACE3+, BCE1-, CCE3+, DCE3+
        mockEditText(cognomeEditText, "") //Arrange
        //Act
        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)
        //Assert
        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_WhiteSpaceSurname_Test() { //ACE3+, BCE2-, CCE3+, DCE3+
        mockEditText(cognomeEditText, "\n") //Arrange
        //Act
        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)
        //Assert
        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_EmptyEmail_Test() { //ACE3+, BCE3+, CCE1-, DCE3+
        mockEditText(emailEditText, "") //Arrange
        //Act
        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)
        //Assert
        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_WhiteSpaceEmail_Test() { //ACE3+, BCE3+, CCE2-, DCE3+
        mockEditText(emailEditText, "\t") //Arrange
        //Act
        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)
        //Assert
        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_EmptyPassword_Test() { //ACE3+, BCE3+, CCE3+, DCE1-
        mockEditText(passwordEditText, "") //Arrange
        //Act
        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)
        //Assert
        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }

    @Test
    fun handleSignUp_WhiteSpacePassword_Test() { //ACE3+, BCE3+, CCE3+, DCE2-
        mockEditText(passwordEditText, " ") //Arrange
        //Act
        signUpController.handleSignUp(nomeEditText, cognomeEditText, emailEditText, passwordEditText)
        //Assert
        verify(signUpController, never()).signUp(any())
        verify(signUpController, times(1)).showToast(anyOrNull(), anyOrNull())
    }
}
