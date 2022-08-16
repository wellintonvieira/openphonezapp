package br.com.wellintonvieira.openphonezapp.ui.adapters

import br.com.wellintonvieira.openphonezapp.data.models.Contact
import br.com.wellintonvieira.openphonezapp.databinding.ContactsItemsBinding
import br.com.wellintonvieira.openphonezapp.util.Constants

class ContactsAdapter(
    val listener: (Contact, String) -> Unit
    ): BaseAdapter<Contact>() {

    override fun bind(binding: ContactsItemsBinding, item: Contact) {
        binding.apply {
            textViewContactName.text = item.name
            textViewContactPhone.text = item.phoneNumber

            imageViewCall.setOnClickListener {
                listener(item, Constants.CALL)
            }

            imageViewWhatsapp.setOnClickListener {
                listener(item, Constants.WHATSAPP)
            }

        }
    }
}