package com.example.myarabicword

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myarabicword.utils.loadLetters
import com.example.myarabicword.utils.persistData
import com.google.android.material.snackbar.Snackbar

class ArabicActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var letternotes: MutableList<LetterNote>
    lateinit var adapter: LettersRecycler
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var currentLetter: LetterNote

    val startForResultNoteDetail = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            processEditNoteResult(result.data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arabic)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        letternotes = mutableListOf()

        val letterNumber = 27
        val letters: List<String> = listOf("ا","ب","ت","ث","ج","ح","خ","د","ذ","ر","ز","س","ش","ص"
            ,"ض","ط","ظ","ع","غ","ف","ق","ك","ل","م","ن","ه","و","ي")

        val lettersStorage = loadLetters(this, "Arabic")

        if (lettersStorage.isNullOrEmpty()) {
            for (i in 0..letterNumber) {
                currentLetter = LetterNote(i, letters[i], lang="Arabic")
                letternotes.add(currentLetter)
                persistData(this, currentLetter)
            }
            adapter = LettersRecycler(letternotes, this)
        } else {
            letternotes = lettersStorage
            adapter = LettersRecycler(lettersStorage, this)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_letter)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        coordinatorLayout = findViewById(R.id.coordinator)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_menu -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onClick(view: View?) {
        if (view?.tag != null) {
            showLetterContent(view.tag as Int)
        }
    }

    private fun saveNote(letterDat : LetterNote, letterIndex: Int) {
        val lastArabicWord = letterDat.dataLetter!!.dicoL.last().first
        val lastFrenchWord = letterDat.dataLetter!!.dicoL.last().second
        letternotes[letterIndex].dataLetter?.dicoL?.add(letterDat.dataLetter!!.dicoL.last())
        letternotes[letterIndex].mot = "$lastArabicWord -> $lastFrenchWord"
        persistData(this, letternotes[letterIndex])
        adapter.notifyDataSetChanged()

        Snackbar.make(coordinatorLayout, "Vous avez appris une nouvelle Kalimah !", Snackbar.LENGTH_LONG)
            .show()
    }

    private fun deleteNote(letterDat: LetterNote, letterIndex: Int, dicoIndex: Int) {
        letternotes[letterIndex].dataLetter!!.dicoL.removeAt(dicoIndex)

        if (letternotes[letterIndex].dataLetter!!.dicoL.size > 1) {
            var firstWord = letternotes[letterIndex].dataLetter!!.dicoL[dicoIndex-1].first
            lateinit var secondWord: String
            if (firstWord.length > 0) {
                secondWord = letternotes[letterIndex].dataLetter!!.dicoL[dicoIndex-1].second
            } else {
                firstWord = letternotes[letterIndex].dataLetter!!.dicoL[dicoIndex].first
                secondWord = letternotes[letterIndex].dataLetter!!.dicoL[dicoIndex].second
            }
            letternotes[letterIndex].mot =  "$firstWord -> $secondWord"
        } else {
            letternotes[letterIndex].mot = "Pas de kalimah pour le moment !"
        }


        persistData(this, letternotes[letterIndex])
        adapter.notifyDataSetChanged()

        val relativeLayoutAct : RecyclerView = findViewById(R.id.recycler_letter)
        Snackbar.make(relativeLayoutAct, "La Kalimah a bien été supprimée.", Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun processEditNoteResult(data: Intent?) {
        val noteLetterIndex = data?.getIntExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, -1)
        val letterDat = data?.getParcelableExtra<LetterNote>(NoteDetailActivity.EXTRA_NOTE)
        when(data?.action!!){
            NoteDetailActivity.SAVE_DATA_LETTER -> {
                saveNote(letterDat!!, noteLetterIndex!!)
            }
            NoteDetailActivity.DELETE_DATA_LETTER -> {
                val dicoIndex = data.getIntExtra(NoteDetailActivity.EXTRA_DICONOTE_INDEX, -1)
                deleteNote(letterDat!!, noteLetterIndex!!, dicoIndex)
            }
        }
    }

    fun showLetterContent(index : Int) {
        val letter = letternotes[index]

        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, letter as Parcelable)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, index)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_LANGUAGE, letter.lang)

        startForResultNoteDetail.launch(intent)
    }

}