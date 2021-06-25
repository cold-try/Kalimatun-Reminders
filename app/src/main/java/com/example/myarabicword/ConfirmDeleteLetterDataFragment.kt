package com.example.myarabicword

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ConfirmDeleteLetterDataFragment(val title : String = "") : DialogFragment() {

    interface ConfirmDeleteLetterDialogFragment {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    var listener: ConfirmDeleteLetterDialogFragment? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        builder.setMessage("Êtes-vous sûr de vouloir supprimer la kalimah \"$title\" ?")
            .setPositiveButton("Supprimer", DialogInterface.OnClickListener { dialog, id -> listener?.onDialogPositiveClick()})
            .setNegativeButton( "Annuler", DialogInterface.OnClickListener { dialog, id -> listener?.onDialogNegativeClick()})
        return builder.create()
    }

}