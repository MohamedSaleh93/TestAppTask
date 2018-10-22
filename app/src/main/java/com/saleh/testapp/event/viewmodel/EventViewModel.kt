package com.saleh.testapp.event.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.parse.ParseObject
import com.parse.ParseQuery
import com.saleh.testapp.model.EventModel
import java.text.SimpleDateFormat

/**
 * Created by mohamedsaleh on 10/22/18.
 */
class EventViewModel : ViewModel() {

    private var eventDetails = MutableLiveData<List<EventModel>>()

    fun getEventDetails(): LiveData<List<EventModel>> {
        return eventDetails
    }

    fun getEventsFromDatabase() {
        val query = ParseQuery.getQuery<ParseObject>("Event")
        query.findInBackground { objects, e ->
            if (e == null) {
                if (objects.size > 0) {
                    var savedEvents = ArrayList<EventModel>()
                    val simpleDateFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                    objects.mapTo(savedEvents) {
                        EventModel(it.getString("address"), it.getInt("numberOfEntranceTicketsBought"),
                                it.getString("city"), it.getString("name"), it.getString("locationName"),
                                simpleDateFormatter.format(it.getDate("startDate")), simpleDateFormatter.format(it.getDate("endDate")))
                    }
                    eventDetails.value = savedEvents
                } else {
                    eventDetails.value = null
                }
            } else {
                eventDetails.value = null
            }
        }
    }

}