package com.example.myarabicword

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myarabicword.utils.persistData
import com.google.android.material.snackbar.Snackbar


class NoteDetailActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var letterTitle : TextView
    lateinit var letterNote : LetterNote
    lateinit var arabWo : TextView
    lateinit var frenchWo : TextView
    lateinit var adapter : LetterDetailRecycler
    lateinit var letterLanguage : String
    var letterIndex : Int = -1

    companion object {
        val EXTRA_NOTE = "com.example.myarabicword"
        val EXTRA_NOTE_INDEX = "noteIndex"
        val SAVE_DATA_LETTER = "com.example.myarabicword.SAVE_DATA_LETTER"
        val DELETE_DATA_LETTER = "com.example.myarabicword.DELETE_DATA_LETTER"
        val EXTRA_DICONOTE_INDEX = "dicoIndex"
        var EXTRA_NOTE_LANGUAGE = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        letterNote = intent.getParcelableExtra(EXTRA_NOTE)!!
        letterIndex = intent.getIntExtra(EXTRA_NOTE_INDEX, -1)
        letterLanguage = intent.getStringExtra(EXTRA_NOTE_LANGUAGE)!!

        /* XML */
        letterTitle = findViewById(R.id.letter_title)
        letterTitle.text = letterNote.letter_string

        arabWo = findViewById(R.id.arab_word)
        frenchWo = findViewById(R.id.french_definition)

        adapter = LetterDetailRecycler(letterNote, this)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_arabic)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_letter_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_menu -> {
                val intentHome = Intent(this, MainActivity::class.java)
                startActivity(intentHome)
                return true
            }
            R.id.save_menu -> {
                saveDataLetter()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showConfirmDeleteDialog(index: Int, title: String, letterIndex: Int){
        val confirmFragment = ConfirmDeleteLetterDataFragment(title)
        confirmFragment.listener = object : ConfirmDeleteLetterDataFragment.ConfirmDeleteLetterDialogFragment{
            override fun onDialogPositiveClick() {
                deleteLine(index, letterIndex)
            }

            override fun onDialogNegativeClick() {
            }
        }
        confirmFragment.show(supportFragmentManager, "confirmDeleteDialog")
    }

    override fun onClick(view: View?) {
        if (view?.tag != null) {
            showConfirmDeleteDialog(view.tag as Int, letterNote.dataLetter!!.dicoL[view.tag as Int].first, letterIndex)
        }
    }

    fun deleteLine(letterIndex: Int, listIndex: Int){
        intent = Intent(DELETE_DATA_LETTER)
        intent.putExtra(EXTRA_NOTE, letterNote as Parcelable)
        intent.putExtra(EXTRA_DICONOTE_INDEX, letterIndex)
        intent.putExtra(EXTRA_NOTE_INDEX, listIndex)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun saveDataLetter(){
        val arabWord : String = arabWo.text.toString()
        val frenchWord : String = frenchWo.text.toString()

        letterNote.dataLetter?.dicoL?.add(Pair(arabWord, frenchWord))

        intent = Intent(SAVE_DATA_LETTER)
        intent.putExtra(EXTRA_NOTE, letterNote as Parcelable)
        intent.putExtra(EXTRA_NOTE_INDEX, letterIndex)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }


}