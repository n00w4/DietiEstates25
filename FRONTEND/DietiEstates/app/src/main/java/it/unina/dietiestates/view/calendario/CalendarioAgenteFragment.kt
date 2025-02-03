package it.unina.dietiestates.view.calendario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import it.unina.dietiestates.R
import it.unina.dietiestates.data.dto.PrenotazioneConInfo
import it.unina.dietiestates.data.model.Annuncio
import it.unina.dietiestates.data.model.Prenotazione
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class CalendarioAgenteFragment : Fragment() {

    private var prenotazioniList: MutableList<PrenotazioneConInfo> = mutableListOf()
    private lateinit var calendarView: MaterialCalendarView
    private lateinit var erroreTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PrenotazioneAgenteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendario_agente, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        erroreTextView = view.findViewById(R.id.erroreTextView)
        erroreTextView.isVisible = false

        calendarView = view.findViewById(R.id.calendarView)
        val today = CalendarDay.today()
        calendarView.selectedDate = today

        recyclerView = view.findViewById(R.id.prenotazioniRecyclerView)
        adapter = PrenotazioneAgenteAdapter(prenotazioniList, requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        loadPrenotazioni()

        calendarView.addDecorator(object : DayViewDecorator {
            override fun shouldDecorate(day: CalendarDay): Boolean {
                // Highlight days with appointments
                return prenotazioniList.any {
                    it.prenotazione.dataInizio.toLocalDate() == day.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() }
            }

            override fun decorate(view: DayViewFacade) {
                view.addSpan(DotSpan(10f, resources.getColor(R.color.colorPrimary, null)))
            }
        })

        calendarView.setOnDateChangedListener { _, date, _ ->
            val selectedDate = date.date
            updateAppointmentsForSelectedDate(selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
        }

        val refreshBtn = view.findViewById<Button>(R.id.refreshButton)
        refreshBtn.setOnClickListener{
            loadPrenotazioni()
        }
    }

    private fun loadPrenotazioni() {
        // Example: Adding some test data
        val annuncio = Annuncio(0, "Casa Blu", "via 123, Napoli, Italia", "no_image", "casa accogliente",
            100, 10000.0f, "piano terra", 3, "A", true, false, true,
            false, true, false)
        val prenotazione = Prenotazione(0, "Feb 02, 2025 10:00:00", "Feb 02, 2025 11:00:00", null,
            "sara.verdi@gmail.it", 0)

        prenotazioniList.add(PrenotazioneConInfo(prenotazione, annuncio))
        prenotazioniList.add(PrenotazioneConInfo(prenotazione, annuncio))
        prenotazioniList.add(PrenotazioneConInfo(prenotazione, annuncio))
    }

    private fun updateAppointmentsForSelectedDate(selectedDate: LocalDate) {
        val filteredAppointments = prenotazioniList
            .filter { it.prenotazione.dataInizio.toLocalDate() == selectedDate }
            .sortedBy { it.prenotazione.dataInizio.toLocalTime() }

        if (filteredAppointments.isNotEmpty()) {
            recyclerView.isVisible = true
            erroreTextView.isVisible = false
            adapter.updateData(filteredAppointments)
        } else {
            recyclerView.isVisible = false
            erroreTextView.isVisible = true
        }
    }

    private fun String.toLocalDate(): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss", Locale.ENGLISH)
        return LocalDate.parse(this, formatter)
    }

    private fun String.toLocalTime(): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss", Locale.ENGLISH)
        return LocalDateTime.parse(this, formatter)
    }

}