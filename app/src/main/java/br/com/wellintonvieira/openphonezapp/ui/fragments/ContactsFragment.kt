package br.com.wellintonvieira.openphonezapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.wellintonvieira.openphonezapp.databinding.FragmentContactsBinding

class ContactsFragment: Fragment() {

    private lateinit var binding: FragmentContactsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }
}