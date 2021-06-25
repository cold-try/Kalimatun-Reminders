package com.example.myarabicword.utils

import android.content.Context
import android.text.TextUtils
import com.example.myarabicword.LetterNote
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.*

private val TAG = "storage"

fun persistData(context: Context, letterDate: LetterNote) {

    if (TextUtils.isEmpty(letterDate.filename)) {
        letterDate.filename = UUID.randomUUID().toString() + ".letterData"
    }

    val fileOutput = context.openFileOutput(letterDate.filename, Context.MODE_PRIVATE)
    val outputStream = ObjectOutputStream(fileOutput)

    outputStream.writeObject(letterDate)
    outputStream.close()

}

fun deleteNote(context: Context, letterNote: LetterNote, letterIndex: Int) {
    val fileOutput = context.openFileOutput(letterNote.filename, Context.MODE_PRIVATE)


}

fun loadLetters(context: Context, language: String) : MutableList<LetterNote> {

    val letters = mutableListOf<LetterNote>()
    val lettersDir = context.filesDir

    for (filename in lettersDir.list()) {
        val letter = loadLetter(context, filename)
        if (letter.lang == language) {
            letters.add(letter)
        }
    }

    return letters
}

fun loadLetter(context: Context, filename: String) : LetterNote {

    val fileInput = context.openFileInput(filename)
    val inputStream = ObjectInputStream(fileInput)
    val letter = inputStream.readObject() as LetterNote
    inputStream.close()

    return letter

}
