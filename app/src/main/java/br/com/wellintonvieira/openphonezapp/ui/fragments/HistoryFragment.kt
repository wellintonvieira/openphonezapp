package br.com.wellintonvieira.openphonezapp.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.wellintonvieira.openphonezapp.R
import br.com.wellintonvieira.openphonezapp.data.models.History
import br.com.wellintonvieira.openphonezapp.databinding.FragmentHistoryBinding
import br.com.wellintonvieira.openphonezapp.presentation.viewmodels.HistoryFragmentViewModel
import br.com.wellintonvieira.openphonezapp.ui.MainActivity
import br.com.wellintonvieira.openphonezapp.ui.adapters.HistoryAdapter
import br.com.wellintonvieira.openphonezapp.util.BottomSheetWhatsapp
import br.com.wellintonvieira.openphonezapp.util.Constants
import br.com.wellintonvieira.openphonezapp.util.openIntent
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment: Fragment(), MenuProvider, SearchView.OnQueryTextListener {

    private lateinit var history: History
    private lateinit var binding: FragmentHistoryBinding
    private val historyViewModel by viewModel<HistoryFragmentViewModel>()
    private val historyAdapter by lazy { HistoryAdapter(this::historyClickListener) }
    private val bottomSheetWhatsapp by lazy { BottomSheetWhatsapp(this::openIntent) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        configureRecyclerView()
        configureObserver()
        return binding.root
    }

    private fun configureRecyclerView() {
        binding.recyclerViewHistoryPhoneNumbers.apply {
            layoutManager = LinearLayoutManager(this@HistoryFragment.context)
            setHasFixedSize(true)
            adapter = historyAdapter
        }
    }

    private fun configureObserver() {
        historyViewModel.load()
        historyViewModel.items.observe(viewLifecycleOwner) {
            if(it.isEmpty()) {
                hideImageHistory(true)
            } else {
                hideImageHistory(false)
                historyAdapter.submitList(it)
            }
        }
    }

    private fun historyClickListener(history: History, action: String) {
        when(action) {
            "click" -> {
                this.history = history
                bottomSheetWhatsapp.create(binding.root.context)
            }
            "delete" -> historyViewModel.delete(history)
        }
    }

    private fun openIntent(action: String) {
        when(action) {
            Constants.ACTION_WHATSAPP -> {
                openIntent(Constants.ACTION_WHATSAPP, "${history.phoneNumber}")
            } else -> {
                openIntent(Constants.ACTION_BUSINESS, "${history.phoneNumber}")
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_search, menu)
        val search = menu.findItem(R.id.menu_search).actionView as SearchView
        search.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return if(menuItem.itemId == R.id.menu_search) {
            (activity as MainActivity).currentItem(2)
            true
        } else {
            false
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { historyViewModel.load(it) }
        historyViewModel.itemsFilter.observe(viewLifecycleOwner) {
            historyAdapter.submitList(it)
        }
        return true
    }

    private fun hideImageHistory(hide: Boolean) {
        if (hide) {
            binding.linearLayoutHistory.visibility = View.VISIBLE
        } else {
            binding.linearLayoutHistory.visibility = View.GONE
        }
    }
}