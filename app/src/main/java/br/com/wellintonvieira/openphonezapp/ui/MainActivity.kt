package br.com.wellintonvieira.openphonezapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.wellintonvieira.openphonezapp.R
import br.com.wellintonvieira.openphonezapp.data.models.Contact
import br.com.wellintonvieira.openphonezapp.databinding.ActivityMainBinding
import br.com.wellintonvieira.openphonezapp.presentation.viewmodels.ContactViewModel
import br.com.wellintonvieira.openphonezapp.ui.adapters.ContactsAdapter
import br.com.wellintonvieira.openphonezapp.util.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val contactViewModel by viewModel<ContactViewModel>()
    private val contactsAdapter by lazy {
        ContactsAdapter(
            this::clickListener
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerViewContacts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = contactsAdapter
        }

        contactViewModel.load()
        contactViewModel.items.observe(this) { items ->
            contactsAdapter.items = items
        }

        binding.floatingButtonAdd.setOnClickListener {
            //TODO ....
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val search = menu.findItem(R.id.menu_search).actionView as SearchView
        search.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { contactViewModel.load(it) }
        return true
    }

    private fun clickListener(item: Contact, type: String) {
        var intent: Intent? = null
        var url: String? = null
        when(type) {
            Constants.CALL -> {
                intent = Intent(Intent.ACTION_CALL)
                url = "tel:${item.phoneNumber}"
            }
            Constants.WHATSAPP -> {
                intent = Intent(Intent.ACTION_VIEW)
                url = "${Constants.WHATSAPP_API}${item.phoneNumber}"
            }
        }
        intent?.let {
            it.data = Uri.parse(url)
            startActivity(it)
        }
    }
}