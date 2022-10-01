package br.com.wellintonvieira.openphonezapp.util

import android.content.Context
import br.com.wellintonvieira.openphonezapp.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BottomSheetWhatsapp(private val openIntent: (action: String) -> Unit) {

    fun create(context: Context) {
        val bottomSheetWhatsapp = BottomSheetDialog(context)
        bottomSheetWhatsapp.setContentView(R.layout.bottom_sheet_whatsapp)
        val whatsapp = bottomSheetWhatsapp.findViewById<FloatingActionButton>(R.id.floating_button_whatsapp)
        val business = bottomSheetWhatsapp.findViewById<FloatingActionButton>(R.id.floating_button_business)
        bottomSheetWhatsapp.setOnDismissListener { it.dismiss() }

        whatsapp?.setOnClickListener {
            openIntent(Constants.ACTION_WHATSAPP)
            bottomSheetWhatsapp.dismiss()
        }

        business?.setOnClickListener {
            openIntent(Constants.ACTION_BUSINESS)
            bottomSheetWhatsapp.dismiss()
        }
        bottomSheetWhatsapp.show()
    }
}