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

        titolo = "Casa Bella"
        descrizione = "Casa spaziosa e confortevole"
        indirizzo = "Via Roma 123"
        latitudine = 41.9028
        longitudine = 12.4964
        prezzo = 500000.0f
        dimensioni = 200
    }

    @Test
    fun isAnyFieldEmpty_ValidInput_Test() {
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
                                            latitudine, longitudine, prezzo, dimensioni)

        assertFalse(result)
    }

    @Test
    fun isAnyFieldEmpty_TitoloIsNull_Test() {
        titolo = null
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)

        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_TitoloIsEmpty_Test() {
        titolo = ""
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)

        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_DescrizioneIsNull_Test() {
        descrizione = null
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)

        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_DescrizioneIsEmpty_Test() {
        descrizione = ""
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)

        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_IndirizzoIsNull_Test() {
        indirizzo = null
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)

        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_IndirizzoIsEmpty_Test() {
        indirizzo = ""
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)

        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_IndirizzoIsNotValid_Test() {
        indirizzo = "Nessun indirizzo corrispondente."
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)

        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_LatitudineIsNull_Test() {
        latitudine = null
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)

        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_LongitudineIsNull_Test() {
        longitudine = null
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)

        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_PrezzoIsNull_Test() {
        prezzo = null
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)

        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_PrezzoIsZero_Test() {
        prezzo = 0.0f
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)

        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_DimensioniIsNull_Test() {
        dimensioni = null
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)

        assertTrue(result)
    }

    @Test
    fun isAnyFieldEmpty_DimensioniIsZeroTest() {
        dimensioni = 0
        val result = dettagliController.isAnyFieldEmpty(titolo, descrizione, indirizzo,
            latitudine, longitudine, prezzo, dimensioni)

        assertTrue(result)
    }

}