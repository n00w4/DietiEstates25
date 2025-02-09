package it.unina.dietiestates

import it.unina.dietiestates.controller.crea_annuncio.CreaAnnuncioDettagliController
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class CreaAnnuncioDettagliControllerTest {
    private lateinit var dettagliController : CreaAnnuncioDettagliController
    private var titolo : String? = null
    private var descrizione : String? = null
    private var indirizzo : String? = null
    private var latitudine : Double? = null
    private var longitudine : Double? = null
    private var prezzo : Float? = null
    private var dimensioni : Int? = null

    @Before
    fun setUp() {
        dettagliController = CreaAnnuncioDettagliController()
        //Arrange
        titolo = "Casa Bella"
        descrizione = "Casa spaziosa e confortevole"
        indirizzo = "Via Roma 123"
        latitudine = 41.9028
        longitudine = 12.4964
        prezzo = 500000.0f
        dimensioni = 200
    }

    @Test
    fun isAnyFieldEmpty_ValidInput_Test() { //ACE3+, BCE3+, CCE4+, DCE2+, ECE2+, FCE3+, GCE3+
        //Act
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
                                            latitudine, longitudine, prezzo, dimensioni)

        assertFalse(result)
    }

    @Test
    fun isAnyFieldEmpty_TitoloIsNull_Test() { //ACE2-, BCE3+, CCE4+, DCE2+, ECE2+, FCE3+, GCE3+
        titolo = null //Arrange
        //Act
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)
        //Assert
        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_TitoloIsEmpty_Test() { //ACE1-, BCE3+, CCE4+, DCE2+, ECE2+, FCE3+, GCE3+
        titolo = "" //Arrange
        //Act
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)
        //Assert
        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_DescrizioneIsNull_Test() { //ACE3+, BCE2-, CCE4+, DCE2+, ECE2+, FCE3+, GCE3+
        descrizione = null //Arrange
        //Act
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)
        //Assert
        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_DescrizioneIsEmpty_Test() { //ACE3+, BCE1-, CCE4+, DCE2+, ECE2+, FCE3+, GCE3+
        descrizione = "" //Arrange
        //Act
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)
        //Assert
        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_IndirizzoIsNull_Test() { //ACE3+, BCE3+, CCE2-, DCE2+, ECE2+, FCE3+, GCE3+
        indirizzo = null //Arrange
        //Act
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)
        //Assert
        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_IndirizzoIsEmpty_Test() { //ACE3+, BCE3+, CCE1-, DCE2+, ECE2+, FCE3+, GCE3+
        indirizzo = "" //Arrange
        //Act
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)
        //Assert
        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_IndirizzoIsNotValid_Test() { //ACE3+, BCE3+, CCE3-, DCE2+, ECE2+, FCE3+, GCE3+
        indirizzo = "Nessun indirizzo corrispondente." //Arrange
        //Act
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)
        //Assert
        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_LatitudineIsNull_Test() { //ACE3+, BCE3+, CCE4+, DCE1-, ECE2+, FCE3+, GCE3+
        latitudine = null //Arrange
        //Act
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)
        //Assert
        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_LongitudineIsNull_Test() { //ACE3+, BCE3+, CCE4+, DCE2+, ECE1-, FCE3+, GCE3+
        longitudine = null //Arrange
        //Act
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)
        //Assert
        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_PrezzoIsNull_Test() { //ACE3+, BCE3+, CCE4+, DCE2+, ECE2+, FCE2-, GCE3+
        prezzo = null //Arrange
        //Act
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)
        //Assert
        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_PrezzoIsZero_Test() { //ACE3+, BCE3+, CCE4+, DCE2+, ECE2+, FCE1-, GCE3+
        prezzo = 0.0f //Arrange
        //Act
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)
        //Assert
        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_DimensioniIsNull_Test() { //ACE3+, BCE3+, CCE4+, DCE2+, ECE2+, FCE3+, GCE2-
        dimensioni = null //Arrange
        //Act
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)
        //Assert
        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_DimensioniIsZeroTest() { //ACE3+, BCE3+, CCE4+, DCE2+, ECE2+, FCE3+, GCE1-
        dimensioni = 0 //Arrange
        //Act
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)
        //Assert
        assertTrue(result)
    }

}
