package br.com.wellintonvieira.openphonezapp.ui.adapters

import br.com.wellintonvieira.openphonezapp.data.models.Contact
import br.com.wellintonvieira.openphonezapp.databinding.ContactsItemsBinding

class ContactsAdapter(val clickListener: (Contact) -> Unit): BaseAdapter<Contact>() {

    override fun bind(binding: ContactsItemsBinding, item: Contact) {
        binding.apply {
            textViewContactPhone.text = item.phoneNumber
            cardViewItems.setOnClickListener {
                clickListener(item)
            }
        }
    }
}