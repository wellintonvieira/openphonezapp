package br.com.wellintonvieira.openphonezapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.wellintonvieira.openphonezapp.data.models.Contact
import br.com.wellintonvieira.openphonezapp.databinding.FragmentMainBinding
import br.com.wellintonvieira.openphonezapp.presentation.viewmodels.ContactViewModel
import br.com.wellintonvieira.openphonezapp.presentation.viewmodels.MainFragmentViewModel
import br.com.wellintonvieira.openphonezapp.util.Constants
import br.com.wellintonvieira.openphonezapp.util.openIntent
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var fragmentButtons: FragmentMainButtons
    private val mainFragmentViewModel by viewModel<MainFragmentViewModel>()
    private val contactsViewModel by viewModel<ContactViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentButtons = FragmentMainButtons(binding, mainFragmentViewModel)
        fragmentButtons.configureNumbersButtons()

        mainFragmentViewModel.text.observe(viewLifecycleOwner) {
            binding.textViewPhoneNumber.text = it
        }

        binding.floatingButtonWhatsapp.setOnClickListener {
            openIntent(Constants.ACTION_WHATSAPP)
        }

        binding.imageViewContactCall.setOnClickListener {
            openIntent(Constants.ACTION_CALL)
        }

        binding.imageViewContactAdd.setOnClickListener {
            openIntent(Constants.ACTION_CONTACT)
        }
    }

    private fun openIntent(action: String) {
        val phoneNumber = binding.textViewPhoneNumber.text
        if (phoneNumber.isNotEmpty()) {
            openIntent(action, "$phoneNumber")
            insertContact("$phoneNumber")
        }
    }

    private fun insertContact(number: String) {
        val contact = Contact(phoneNumber = number)
        contactsViewModel.insert(contact)
    }
}