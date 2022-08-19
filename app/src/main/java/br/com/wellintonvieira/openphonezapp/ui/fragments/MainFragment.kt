package br.com.wellintonvieira.openphonezapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.wellintonvieira.openphonezapp.databinding.FragmentMainBinding
import br.com.wellintonvieira.openphonezapp.presentation.viewmodels.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var fragmentButtons: FragmentMainButtons
    private val mainFragmentViewModel by viewModel<MainFragmentViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentButtons = FragmentMainButtons(binding, mainFragmentViewModel)
        fragmentButtons.configureOnClickListeners()
        mainFragmentViewModel.getNumbers().observe(viewLifecycleOwner) {

        }
    }
}