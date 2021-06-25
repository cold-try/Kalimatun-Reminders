package com.example.myarabicword

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class LetterDetailRecycler(val letterPair: LetterNote, val itemClickListener: View.OnClickListener)
    : RecyclerView.Adapter<LetterDetailRecycler.ViewHolder>(){

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.card_arab_item)
        val arabWord = itemView.findViewById<TextView>(R.id.kalimatun_arab)
        val frenchWord = itemView.findViewById<TextView>(R.id.trad_fr)
        val arrowIcon = itemView.findViewById<ImageView>(R.id.arrow_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.arab_item, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val letterKalim = letterPair.dataLetter!!.dicoL[position]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        if (letterKalim.first.length == 0) {
            holder.arrowIcon.visibility = View.GONE
        }
        holder.arabWord.text = letterKalim.first
        holder.frenchWord.text = letterKalim.second
    }

    override fun getItemCount(): Int {
        return letterPair.dataLetter!!.dico.count()
    }

}