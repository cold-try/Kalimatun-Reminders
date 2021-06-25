package com.example.myarabicword

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class LettersRecycler(val notes: MutableList<LetterNote>, val itemClickListener: View.OnClickListener) : RecyclerView.Adapter<LettersRecycler.ViewHolder>(){

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.arab_card)
        val imageLetter = itemView.findViewById<ImageView>(R.id.image_letter)
        val previewLetter = itemView.findViewById<TextView>(R.id.previewLetter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.arab_list_item, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentLetter = notes[position]
        val letterIndex = currentLetter.letter

        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        holder.previewLetter.text = currentLetter.mot

        when (letterIndex) {
            0 -> holder.imageLetter.setImageResource(R.drawable.lettre0)
            1 -> holder.imageLetter.setImageResource(R.drawable.lettre1)
            2 -> holder.imageLetter.setImageResource(R.drawable.lettre2)
            3 -> holder.imageLetter.setImageResource(R.drawable.lettre3)
            4 -> holder.imageLetter.setImageResource(R.drawable.lettre4)
            5 -> holder.imageLetter.setImageResource(R.drawable.lettre5)
            6 -> holder.imageLetter.setImageResource(R.drawable.lettre6)
            7 -> holder.imageLetter.setImageResource(R.drawable.lettre7)
            8 -> holder.imageLetter.setImageResource(R.drawable.lettre8)
            9 -> holder.imageLetter.setImageResource(R.drawable.lettre9)
            10 -> holder.imageLetter.setImageResource(R.drawable.lettre10)
            11 -> holder.imageLetter.setImageResource(R.drawable.lettre11)
            12 -> holder.imageLetter.setImageResource(R.drawable.lettre12)
            13 -> holder.imageLetter.setImageResource(R.drawable.lettre13)
            14 -> holder.imageLetter.setImageResource(R.drawable.lettre14)
            15 -> holder.imageLetter.setImageResource(R.drawable.lettre15)
            16 -> holder.imageLetter.setImageResource(R.drawable.lettre16)
            17 -> holder.imageLetter.setImageResource(R.drawable.lettre17)
            18 -> holder.imageLetter.setImageResource(R.drawable.lettre18)
            19 -> holder.imageLetter.setImageResource(R.drawable.lettre19)
            20 -> holder.imageLetter.setImageResource(R.drawable.lettre20)
            21 -> holder.imageLetter.setImageResource(R.drawable.lettre21)
            22 -> holder.imageLetter.setImageResource(R.drawable.lettre22)
            23 -> holder.imageLetter.setImageResource(R.drawable.lettre23)
            24 -> holder.imageLetter.setImageResource(R.drawable.lettre24)
            25 -> holder.imageLetter.setImageResource(R.drawable.lettre25)
            26 -> holder.imageLetter.setImageResource(R.drawable.lettre26)
            27 -> holder.imageLetter.setImageResource(R.drawable.lettre27)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }


}