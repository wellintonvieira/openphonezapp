package br.com.wellintonvieira.openphonezapp.ui.fragments

import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import br.com.wellintonvieira.openphonezapp.data.models.History
import br.com.wellintonvieira.openphonezapp.databinding.FragmentMainBinding
import br.com.wellintonvieira.openphonezapp.presentation.viewmodels.HistoryFragmentViewModel
import br.com.wellintonvieira.openphonezapp.presentation.viewmodels.MainFragmentViewModel
import br.com.wellintonvieira.openphonezapp.util.BottomSheetWhatsapp
import br.com.wellintonvieira.openphonezapp.util.openIntent
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

    @SuppressLint("SetTextI18n")
    private fun configureListeners() {
        binding.floatingButtonOpenWhatsapp.setOnClickListener {
            val phoneNumber = binding.textViewPhoneNumber.text
            if (phoneNumber.isNotEmpty()) {
                bottomSheetWhatsapp.create(binding.root.context)
            }
        }

        binding.buttonBackspace.setOnClickListener {
            mainFragmentViewModel.remove()
        }

        binding.buttonBackspace.setOnLongClickListener() {
            mainFragmentViewModel.clear()
            return@setOnLongClickListener true
        }
    }

    @SuppressLint("SetTextI18n")
    fun pasteClipboard() {
        val clipboard = binding.root.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val text = clipboard.primaryClip?.getItemAt(0)
        if (text != null) {
            val phoneNumber = text.text.toString().replace("[^0-9]".toRegex(), "")
            binding.textViewPhoneNumber.text = "${binding.textViewPhoneNumber.text}${phoneNumber.trim()}"
            for(number in phoneNumber) {
                mainFragmentViewModel.add(number)
            }
        }
    }

    private fun onClickListener(keyNumber: Char) {
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
}