package com.formation.agrikoliskotlin

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Note(
    var title: String = "",
    var text: String = "",
    var filename: String = ""
) : Parcelable, Serializable {

    private val serialVersionUID: Long = 42424242424242L

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(text)
        parcel.writeString(filename)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }
}
