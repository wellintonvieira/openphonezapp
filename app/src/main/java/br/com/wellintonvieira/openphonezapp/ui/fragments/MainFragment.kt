package br.com.wellintonvieira.openphonezapp.ui.fragments

import android.Manifest
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import br.com.wellintonvieira.openphonezapp.R
import br.com.wellintonvieira.openphonezapp.data.models.History
import br.com.wellintonvieira.openphonezapp.databinding.FragmentMainBinding
import br.com.wellintonvieira.openphonezapp.presentation.viewmodels.HistoryFragmentViewModel
import br.com.wellintonvieira.openphonezapp.presentation.viewmodels.MainFragmentViewModel
import br.com.wellintonvieira.openphonezapp.util.BottomSheetWhatsapp
import br.com.wellintonvieira.openphonezapp.util.Constants
import br.com.wellintonvieira.openphonezapp.util.openIntent
import br.com.wellintonvieira.openphonezapp.util.showSnack
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var fragmentButtons: MainFragmentButtons
    private val mainFragmentViewModel by viewModel<MainFragmentViewModel>()
    private val historyViewModel by viewModel<HistoryFragmentViewModel>()
    private val bottomSheetWhatsapp by lazy { BottomSheetWhatsapp(this::openIntent) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentButtons = MainFragmentButtons(binding, this::onClickListener)
        fragmentButtons.configureNumbersButtons()

        mainFragmentViewModel.text.observe(viewLifecycleOwner) {
            binding.textViewPhoneNumber.text = it
        }
        configureListeners()
    }

    private fun configureListeners() {
        binding.floatingButtonOpenWhatsapp.setOnClickListener {
            val phoneNumber = binding.textViewPhoneNumber.text
            if (phoneNumber.isNotEmpty()) {
                bottomSheetWhatsapp.create(binding.root.context)
            }
        }

        binding.imageViewPhoneNumberPaste.setOnClickListener {
            val clipboard = binding.root.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val text = clipboard.primaryClip?.getItemAt(0)
            if (text != null) {
                val phoneNumber = text.text.toString().replace("[^0-9]".toRegex(), "")
                binding.textViewPhoneNumber.text = phoneNumber.trim()
            }
        }

        binding.imageViewCallPhoneNumber.setOnClickListener {
            val phoneNumber = binding.textViewPhoneNumber.text
            if (phoneNumber.isNotEmpty()) {
                requestPermission.launch(Manifest.permission.CALL_PHONE)
            }
        }

        binding.imageViewPhoneNumberAdd.setOnClickListener {
            val phoneNumber = binding.textViewPhoneNumber.text
            if (phoneNumber.isNotEmpty()) {
                openIntent(Constants.ACTION_CONTACT)
            }
        }

        binding.buttonBackspace.setOnClickListener {
            mainFragmentViewModel.remove()
        }

        binding.buttonBackspace.setOnLongClickListener() {
            mainFragmentViewModel.clear()
            return@setOnLongClickListener true
        }

        binding.buttonClear.setOnClickListener {
            mainFragmentViewModel.clear()
        }
    }

    private fun onClickListener(keyNumber: Int) {
        mainFragmentViewModel.add(keyNumber)
    }

    private fun openIntent(action: String) {
        val phoneNumber = binding.textViewPhoneNumber.text
        if (phoneNumber.isNotEmpty()) {
            openIntent(action, "$phoneNumber")
            insertPhoneNumber("$phoneNumber")
            mainFragmentViewModel.clear()
        }
    }

    private fun insertPhoneNumber(number: String) {
        val contact = History(phoneNumber = number.toLong())
        historyViewModel.insert(contact)
    }

    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        if (result) {
            openIntent(Constants.ACTION_CALL)
        } else {
            view?.let {
                showSnack(it, getString(R.string.permission_call_phone))
            }
        }
    }
}