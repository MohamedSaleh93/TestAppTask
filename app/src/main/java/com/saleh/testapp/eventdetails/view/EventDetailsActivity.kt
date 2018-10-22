package com.saleh.testapp.eventdetails.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.saleh.testapp.R
import com.saleh.testapp.model.EventModel
import kotlinx.android.synthetic.main.activity_event_details.*
import java.text.SimpleDateFormat

class EventDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        val eventModel = intent.getParcelableExtra<EventModel>("eventModel")
        setDataToUI(eventModel)
    }

    private fun setDataToUI(eventModel: EventModel?) {
        eventNameTV.text = "Event name: " + eventModel?.name
        eventAddressTV.text = "Event Address: " + eventModel?.address
        eventCityTV.text = "Event city: " + eventModel?.city
        numberOfEntranceTicketsBoughtTV.text = "Bought tickets: " + eventModel?.numberOfEntranceTicketsBought
        locationNameTV.text = "Location name: " + eventModel?.locationName
        startDateTV.text = "Start date: " + eventModel?.startDate
        endDateTV.text =  "End date: " + eventModel?.endDate
    }
}
