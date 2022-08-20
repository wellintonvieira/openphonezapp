package br.com.wellintonvieira.openphonezapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.wellintonvieira.openphonezapp.data.models.Contact
import br.com.wellintonvieira.openphonezapp.databinding.FragmentContactsBinding
import br.com.wellintonvieira.openphonezapp.presentation.viewmodels.ContactViewModel
import br.com.wellintonvieira.openphonezapp.ui.adapters.ContactsAdapter
import br.com.wellintonvieira.openphonezapp.util.Constants
import br.com.wellintonvieira.openphonezapp.util.openIntent
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsFragment: Fragment() {

    private lateinit var binding: FragmentContactsBinding
    private val contactsViewModel by viewModel<ContactViewModel>()
    private val contactsAdapter by lazy { ContactsAdapter(this::contactClickListener) }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentContactsBinding.inflate(inflater, container, false)
        configureRecyclerView()
        contactsViewModel.load()
        contactsViewModel.items.observe(viewLifecycleOwner) {
            contactsAdapter.items = it
            contactsAdapter.notifyDataSetChanged()
        }
        return binding.root
    }

    private fun configureRecyclerView() {
        binding.recyclerViewContacts.apply {
            layoutManager = LinearLayoutManager(this@ContactsFragment.context)
            setHasFixedSize(true)
            adapter = contactsAdapter
        }
    }

    private fun contactClickListener(contact: Contact) {
        openIntent(Constants.WHATSAPP_API, contact.phoneNumber)
    }
}