package com.example.myarabicword

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

class LetterNote(val letter: Int, val letter_string: String?, var mot: String? = "Pas de kalimah pour le moment !",
                 var filename: String? = "", var dataLetter: Kalimah? = Kalimah(mutableListOf(Pair("", ""))), val lang: String?)
    : Parcelable, Serializable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Kalimah::class.java.classLoader)!!,
        parcel.readString(),
    )

    @Parcelize
    class Kalimah(var dico: MutableList<Pair<String,String>>) : Parcelable, Serializable {
        var dicoL = dico
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(letter)
        parcel.writeString(letter_string)
        parcel.writeString(mot)
        parcel.writeString(filename)
        parcel.writeParcelable(dataLetter, flags)
        parcel.writeString(lang)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LetterNote> {
        private val serialVersionUID: Long = 248274234827
        override fun createFromParcel(parcel: Parcel): LetterNote {
            return LetterNote(parcel)
        }

        override fun newArray(size: Int): Array<LetterNote?> {
            return arrayOfNulls(size)
        }
    }


}