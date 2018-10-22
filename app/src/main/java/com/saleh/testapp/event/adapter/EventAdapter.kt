package com.saleh.testapp.event.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saleh.testapp.R
import com.saleh.testapp.eventdetails.view.EventDetailsActivity
import com.saleh.testapp.model.EventModel
import kotlinx.android.synthetic.main.events_details_list_item.view.*

class EventAdapter(private var context: Context, private var eventsData: List<EventModel>): RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.eventName.text = eventsData[position].name
        holder.eventAddress.text = eventsData[position].address
        holder.eventCity.text = eventsData[position].city
        holder.detailsBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, EventDetailsActivity::class.java)
            intent.putExtra("eventModel", eventsData[position])
            context.startActivity(intent)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(LayoutInflater.from(context).inflate(R.layout.events_details_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return eventsData.size
    }


    class EventViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val eventName = view.eventNameTV
        val eventAddress = view.eventAddressTV
        val eventCity = view.eventCityTV
        val detailsBtn = view.detailsBtn
    }

}