package com.example.bolbol.models

import android.os.Parcel
import android.os.Parcelable
import android.provider.ContactsContract

data class Customer(var name:String?,var phoneNumber: String?, var email: String?,
                    var password:String? , var confirmPassword:String? ,
                    var cityid:String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(phoneNumber)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeString(confirmPassword)
        parcel.writeString(cityid)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Customer> {
        override fun createFromParcel(parcel: Parcel): Customer {
            return Customer(parcel)
        }

        override fun newArray(size: Int): Array<Customer?> {
            return arrayOfNulls(size)
        }
    }
}