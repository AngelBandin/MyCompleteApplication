package com.example.mycompleteaplication.ui.calendar.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mycompleteaplication.databinding.ItemCalendarEventBinding
import com.example.mycompleteaplication.data.model.EventModel
import com.example.mycompleteaplication.domain.model.Event


class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemCalendarEventBinding.bind(itemView)
    fun bind(evento: Event, position: Int, onItemSelected: (Int) -> Unit) {
        binding.tvEvent.text = evento.nombre
        binding.tvHoraInicio.text = evento.horaInicio
        binding.tvHoraFin.text = evento.horaFin
        binding.cbTask.isChecked = evento.checked
        binding.cbTask.setOnClickListener(){
            onItemSelected(position)
        }
    }
}