package com.test.flighttestapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.flighttestapp.MainViewModel
import com.test.flighttestapp.databinding.LayoutRowFlightBinding
import com.test.flighttestapp.models.response.J
import com.test.flighttestapp.view_models.FlightViewModel

class FlightsAdapter(private val viewModel: MainViewModel) :
    RecyclerView.Adapter<FlightsAdapter.FlightsViewHolder>() {
    var flights = mutableListOf<J>()

    fun setFlightList(flights: List<J>) {
        this.flights = flights.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutRowFlightBinding.inflate(inflater, parent, false)
        return FlightsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlightsViewHolder, position: Int) {
        val flight = flights[position]
        holder.binding.viewModel = FlightViewModel(viewModel.airLineMaster, flight)
        holder.binding.viewModel?.setRowData()
    }

    override fun getItemCount(): Int {
        return flights.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class FlightsViewHolder(val binding: LayoutRowFlightBinding) :
        RecyclerView.ViewHolder(binding.root)

}