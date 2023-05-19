package com.dauto.moongarden.location

import android.app.AlertDialog
import android.content.Context

object DialogManager {
    fun locationSettingsDialog(context: Context, listener: Listener) {
        val builder = AlertDialog.Builder(context)
        val dialog = builder.create()
        dialog.setTitle("Включить геолокацию?")
        dialog.setMessage("Геолокация выключена, хотите включить?")
         dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { _, _ ->
            listener.onClickPositive()
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cansel") { _, _ ->
            listener.onClickNegative()
            dialog.dismiss()
        }
        dialog.show()

    }

    interface Listener {
        fun onClickPositive()
        fun onClickNegative()
    }
}