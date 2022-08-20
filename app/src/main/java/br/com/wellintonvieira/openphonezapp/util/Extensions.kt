package br.com.wellintonvieira.openphonezapp.util

import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import androidx.fragment.app.Fragment

fun Fragment.openIntent(action: String, phoneNumber: String) {
    val intent = when(action) {
        Constants.ACTION_CALL -> {
            Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
        }
        Constants.ACTION_CONTACT -> {
            Intent(Intent.ACTION_INSERT).apply {
                type = ContactsContract.Contacts.CONTENT_TYPE
                putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber)
            }
        }
        else -> {
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("${Constants.WHATSAPP_API}$phoneNumber")
            }
        }
    }
    startActivity(intent)
}