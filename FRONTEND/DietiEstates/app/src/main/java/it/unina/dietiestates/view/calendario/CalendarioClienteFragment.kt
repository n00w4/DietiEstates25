package it.unina.dietiestates.view.calendario

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
import it.unina.dietiestates.controller.calendario.CalendarioClienteController
import it.unina.dietiestates.data.dto.PrenotazioneConInfo
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class CalendarioClienteFragment : Fragment() {

    private var prenotazioniList: MutableList<PrenotazioneConInfo> = mutableListOf()
    private lateinit var calendarView: MaterialCalendarView
    private lateinit var erroreTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PrenotazioneClienteAdapter
    private lateinit var controller: CalendarioClienteController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendario_cliente, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        erroreTextView = view.findViewById(R.id.erroreTextView)
        erroreTextView.isVisible = false

        calendarView = view.findViewById(R.id.calendarView)
        val today = CalendarDay.today()
        calendarView.selectedDate = today

        recyclerView = view.findViewById(R.id.prenotazioniRecyclerView)
        adapter = PrenotazioneClienteAdapter(prenotazioniList, requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        controller = CalendarioClienteController(requireContext())
        loadPrenotazioni()

        applyCalendarDecorator()

        calendarView.setOnDateChangedListener { _, date, _ ->
            val selectedDate = date.date
            updateAppointmentsForSelectedDate(selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
        }

        val refreshBtn = view.findViewById<Button>(R.id.refreshButton)
        refreshBtn.setOnClickListener{
            loadPrenotazioni()
            calendarView.selectedDate = today
        }
    }

    private fun loadPrenotazioni() {
        Log.d("CalendarioClienteFragment", "loadPrenotazioni() called")
        controller.getPrenotazioniCliente { result ->
            if (result.isSuccess) {
                val listaResult = result.getOrNull()
                listaResult?.let {
                    prenotazioniList.clear()
                    prenotazioniList.addAll(it)
                    applyCalendarDecorator()
                }
            } else if (result.isFailure) {
                val error = result.exceptionOrNull()?.message
                erroreTextView.isVisible = true
                if (isAdded) { // Controllo fragment esistente prima di chiamare requireContext() (prevenzione di errori a runtime)
                    Toast.makeText(requireContext(), "$error", Toast.LENGTH_SHORT).show()
                }
            }
        }
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

    private fun applyCalendarDecorator() {
        calendarView.removeDecorators() // Remove any existing decorators
        calendarView.addDecorator(object : DayViewDecorator {
            override fun shouldDecorate(day: CalendarDay): Boolean {
                return prenotazioniList.any {
                    it.prenotazione.dataInizio.toLocalDate() == day.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                }
            }
            override fun decorate(view: DayViewFacade) {
                view.addSpan(DotSpan(10f, resources.getColor(R.color.colorPrimary, null)))
            }
        })
    }

}