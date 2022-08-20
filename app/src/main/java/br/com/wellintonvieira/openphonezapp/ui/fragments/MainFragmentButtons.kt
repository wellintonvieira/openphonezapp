package br.com.wellintonvieira.openphonezapp.ui.fragments

import br.com.wellintonvieira.openphonezapp.databinding.FragmentMainBinding

class MainFragmentButtons(
    private val binding: FragmentMainBinding,
    private val onClickListener: (Int) -> Unit) {

    fun configureNumbersButtons() {
        binding.button0.setOnClickListener {
            onClickListener(0)
        }

        binding.button1.setOnClickListener {
            onClickListener(1)
        }

        binding.button2.setOnClickListener {
            onClickListener(2)
        }

        binding.button3.setOnClickListener {
            onClickListener(3)
        }

        binding.button4.setOnClickListener {
            onClickListener(4)
        }

        binding.button5.setOnClickListener {
            onClickListener(5)
        }

        binding.button6.setOnClickListener {
            onClickListener(6)
        }

        binding.button7.setOnClickListener {
            onClickListener(7)
        }

        binding.button8.setOnClickListener {
            onClickListener(8)
        }

        binding.button9.setOnClickListener {
            onClickListener(9)
        }
    }
}