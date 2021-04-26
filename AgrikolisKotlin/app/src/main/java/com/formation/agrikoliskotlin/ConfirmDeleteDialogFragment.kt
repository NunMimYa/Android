package com.formation.agrikoliskotlin

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ConfirmDeleteDialogFragment(val noteTitle: String): DialogFragment() {

    interface ConfirmDeleteDialogListener{
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    var listener: ConfirmDeleteDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)

        builder.setMessage("Êtes-vous sûr de supprimer la note ayant pour titre :\n$noteTitle ?")
            .setPositiveButton("Supprimer"){dialog, id -> listener?.onDialogPositiveClick()}
            .setNegativeButton("Annuler"){dialog, id -> listener?.onDialogNegativeClick()}

        return builder.create()
    }

}