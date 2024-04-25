package com.example.mycompleteaplication.ui.calendar

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.InvalidationTracker
import com.example.mycompleteaplication.R
import com.example.mycompleteaplication.databinding.FragmentCalendarBinding
import com.example.mycompleteaplication.data.model.EventModel
import com.example.mycompleteaplication.domain.model.Event
import com.example.mycompleteaplication.ui.calendar.adapter.CalendarAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 * A simple [Fragment] subclass.
 * Use the [CalendarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CalendarFragment : Fragment() {
    private val calendarViewModel: CalendarViewModel by viewModels<CalendarViewModel>()
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    private lateinit var calendarAdapter: CalendarAdapter
    private var date: Long =0
    private val eventoseliminar: MutableMap<Long, MutableList<Int>> = mutableMapOf()
    private var eventosPorDia: MutableMap<Long, List<Event>> = mutableMapOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCalendarBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        lifecycleScope.launch {calendarViewModel.insertEvents(eventosPorDia)}
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                calendarViewModel.isLoading.collect {
                    when (it) {
                        true -> {
                            binding.loading.isVisible = true
                            binding.dpCalendar.isVisible = false
                        }
                        false -> {
                            binding.loading.isVisible = false
                            binding.dpCalendar.isVisible = true
                            initUI()
                        }
                    }
                }
            }
        }
    }

    private fun initUI(){
        eventosPorDia = calendarViewModel.eventosPorDia.toMutableMap()
        initRecyclerView()
        initListeners()
    }

    private fun initRecyclerView() {
        val current = LocalDate.now()
        date = "${current.year}${current.monthValue - 1}${current.dayOfMonth}".toLong()
        val eventosDelDia = eventosPorDia[date] ?: emptyList()
        calendarAdapter = CalendarAdapter(eventosDelDia){position -> onItemSelected(position)}
        binding.rvEventos.apply {
            adapter= calendarAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initListeners() {
        binding.dpCalendar.setOnDateChangeListener {_, year, month, dayOfMonth ->
            date = "$year$month$dayOfMonth".toLong()
            val eventosDelDia = eventosPorDia[date] ?: emptyList()
            calendarAdapter.actualizarEventos(eventosDelDia)
        }

        binding.buttonAddCalendar.setOnClickListener {
            showDialog()

        }
        binding.buttonRemoveCalendar.setOnClickListener{
            eliminarEventos()
        }
    }
    private fun onItemSelected(position: Int){
        val aux = eventosPorDia[date].orEmpty().toMutableList()
        aux[position].checked = !aux[position].checked
        if (!aux[position].checked){
            if (eventoseliminar.contains(date) && eventoseliminar[date]?.contains(position) == true){
                eventoseliminar[date]?.remove(position)
                if (eventoseliminar[date]?.isEmpty() == true){
                    eventoseliminar.remove(date)
                }

            }
            if (eventoseliminar.isEmpty()){
                binding.buttonRemoveCalendar.isVisible = false
            }
        }
        else{
            if (eventoseliminar[date] == null) eventoseliminar[date] = mutableListOf()
            if (eventoseliminar[date]?.contains(position) == false) {
                eventoseliminar[date]?.add(position)
            }
            if (!binding.buttonRemoveCalendar.isVisible) binding.buttonRemoveCalendar.isVisible =  true
        }
        eventosPorDia[date] = aux
    }
    private fun eliminarEventos(){
        for (i in eventoseliminar.keys) {
            val aux = eventosPorDia[i].orEmpty().toMutableList()
            eventosPorDia[i]?.filter { it.checked }?.forEach {
                aux.remove(it)
            }
            eventosPorDia[i] = aux

        }
        calendarAdapter.actualizarEventos(eventosPorDia[date].orEmpty())
        binding.buttonRemoveCalendar.isVisible = false
    }

    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_calendar_event)
        val etNombre: EditText = dialog.findViewById(R.id.etNombreEvento)
        val etHoraInicio: EditText = dialog.findViewById(R.id.etInicioEvento)
        val etHoraFin: EditText = dialog.findViewById(R.id.etFinEvento)
        val btAceptar: Button = dialog.findViewById(R.id.btAceptarEvento)
        val btCancelar: Button = dialog.findViewById(R.id.btCancelarEvento)
        etHoraInicio.setOnClickListener {
           setTimePicker(etHoraInicio)
        }
        etHoraFin.setOnClickListener {
            setTimePicker(etHoraFin)
        }
        btAceptar.setOnClickListener{
            anadirEvento(etNombre.text.toString(),etHoraInicio.text.toString(), etHoraFin.text.toString())
            dialog.dismiss()
        }
        btCancelar.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun setTimePicker(et: EditText) {
        val timeSetListener= TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            et.setText("$hourOfDay:$minute")
        }
        TimePickerDialog(requireContext(),timeSetListener,0,0,true).show()
    }

    private fun anadirEvento(nombre: String, inicio: String, fin: String) {
        val aux = eventosPorDia[date].orEmpty().toMutableList()
        aux.add(Event(nombre,inicio,fin))
        eventosPorDia[date]=aux
        calendarAdapter.actualizarEventos(eventosPorDia[date].orEmpty())


    }
}