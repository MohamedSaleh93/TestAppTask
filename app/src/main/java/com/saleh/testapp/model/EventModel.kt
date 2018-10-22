package com.saleh.testapp.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by mohamedsaleh on 10/22/18.
 */
data class EventModel(var address: String?, var numberOfEntranceTicketsBought: Int, var city: String?,
                      var name: String?, var locationName: String?, var startDate: String?, var endDate: String?): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeInt(numberOfEntranceTicketsBought)
        parcel.writeString(city)
        parcel.writeString(name)
        parcel.writeString(locationName)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventModel> {
        override fun createFromParcel(parcel: Parcel): EventModel {
            return EventModel(parcel)
        }

        override fun newArray(size: Int): Array<EventModel?> {
            return arrayOfNulls(size)
        }
    }


}