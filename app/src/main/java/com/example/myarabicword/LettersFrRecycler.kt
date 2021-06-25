package com.example.myarabicword

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class LettersFrRecycler(val notes: MutableList<LetterNote>, val itemClickListener: View.OnClickListener) : RecyclerView.Adapter<LettersFrRecycler.ViewHolder>(){

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
            0 -> holder.imageLetter.setImageResource(R.drawable.lettrefr1)
            1 -> holder.imageLetter.setImageResource(R.drawable.lettrefr2)
            2 -> holder.imageLetter.setImageResource(R.drawable.lettrefr3)
            3 -> holder.imageLetter.setImageResource(R.drawable.lettrefr4)
            4 -> holder.imageLetter.setImageResource(R.drawable.lettrefr5)
            5 -> holder.imageLetter.setImageResource(R.drawable.lettrefr6)
            6 -> holder.imageLetter.setImageResource(R.drawable.lettrefr7)
            7 -> holder.imageLetter.setImageResource(R.drawable.lettrefr8)
            8 -> holder.imageLetter.setImageResource(R.drawable.lettrefr9)
            9 -> holder.imageLetter.setImageResource(R.drawable.lettrefr10)
            10 -> holder.imageLetter.setImageResource(R.drawable.lettrefr11)
            11 -> holder.imageLetter.setImageResource(R.drawable.lettrefr12)
            12 -> holder.imageLetter.setImageResource(R.drawable.lettrefr13)
            13 -> holder.imageLetter.setImageResource(R.drawable.lettrefr14)
            14 -> holder.imageLetter.setImageResource(R.drawable.lettrefr15)
            15 -> holder.imageLetter.setImageResource(R.drawable.lettrefr16)
            16 -> holder.imageLetter.setImageResource(R.drawable.lettrefr17)
            17 -> holder.imageLetter.setImageResource(R.drawable.lettrefr18)
            18 -> holder.imageLetter.setImageResource(R.drawable.lettrefr19)
            19 -> holder.imageLetter.setImageResource(R.drawable.lettrefr20)
            20 -> holder.imageLetter.setImageResource(R.drawable.lettrefr21)
            21 -> holder.imageLetter.setImageResource(R.drawable.lettrefr22)
            22 -> holder.imageLetter.setImageResource(R.drawable.lettrefr23)
            23 -> holder.imageLetter.setImageResource(R.drawable.lettrefr24)
            24 -> holder.imageLetter.setImageResource(R.drawable.lettrefr25)
            25 -> holder.imageLetter.setImageResource(R.drawable.lettrefr26)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }


}