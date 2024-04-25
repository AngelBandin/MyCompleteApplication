package com.example.mycompleteaplication.ui.calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycompleteaplication.R
import com.example.mycompleteaplication.data.model.EventModel
import com.example.mycompleteaplication.domain.model.Event

class CalendarAdapter (private var eventos: List<Event>, private val onItemSelected:(Int) -> Unit
): RecyclerView.Adapter<CalendarViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_event, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return eventos.size
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(eventos[position], position, onItemSelected)
    }
    fun actualizarEventos(eventosDelDia: List<Event>) {
        eventos = eventosDelDia
        notifyDataSetChanged()
    }

}