package br.com.wellintonvieira.openphonezapp.ui.fragments

import br.com.wellintonvieira.openphonezapp.databinding.FragmentMainBinding
import br.com.wellintonvieira.openphonezapp.presentation.viewmodels.MainFragmentViewModel

class FragmentMainButtons(
    private val binding: FragmentMainBinding,
    private val mainFragmentViewModel: MainFragmentViewModel
) {

    fun configureOnClickListeners() {
        binding.button0.setOnClickListener {
            mainFragmentViewModel.add(0)
        }

        binding.button1.setOnClickListener {
            mainFragmentViewModel.add(1)
        }

        binding.button2.setOnClickListener {
            mainFragmentViewModel.add(2)
        }

        binding.button3.setOnClickListener {
            mainFragmentViewModel.add(3)
        }

        binding.button4.setOnClickListener {
            mainFragmentViewModel.add(4)
        }

        binding.button5.setOnClickListener {
            mainFragmentViewModel.add(5)
        }

        binding.button6.setOnClickListener {
            mainFragmentViewModel.add(6)
        }

        binding.button7.setOnClickListener {
            mainFragmentViewModel.add(7)
        }

        binding.button8.setOnClickListener {
            mainFragmentViewModel.add(9)
        }

        binding.button9.setOnClickListener {
            mainFragmentViewModel.add(9)
        }

        binding.buttonBackspace.setOnClickListener {
            mainFragmentViewModel.remove()
        }
    }
}