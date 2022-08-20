package br.com.wellintonvieira.openphonezapp.util

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.view.View
import androidx.fragment.app.Fragment
import br.com.wellintonvieira.openphonezapp.R
import com.google.android.material.snackbar.Snackbar

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
    try {
        startActivity(intent)
    } catch (exception: ActivityNotFoundException) {
        view?.let {
            showSnack(it, getString(R.string.activity_not_found_exception, exception.message))
        }
    }
}

fun showSnack(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}