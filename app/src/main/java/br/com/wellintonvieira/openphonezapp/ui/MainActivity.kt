package br.com.wellintonvieira.openphonezapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.wellintonvieira.openphonezapp.R
import br.com.wellintonvieira.openphonezapp.databinding.ActivityMainBinding
import br.com.wellintonvieira.openphonezapp.ui.adapters.PageAdapter
import br.com.wellintonvieira.openphonezapp.ui.fragments.ContactsFragment
import br.com.wellintonvieira.openphonezapp.ui.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        configureViewPager()
    }

    private fun configureViewPager() {
        val pageAdapter = PageAdapter(supportFragmentManager)
        pageAdapter.addFragment(MainFragment(), getString(R.string.tab_title_phone))
        pageAdapter.addFragment(Fragment(), getString(R.string.tab_title_registers))
        pageAdapter.addFragment(ContactsFragment(), getString(R.string.tab_title_contacts))
        binding.viewPagerMain.adapter = pageAdapter
        binding.tabLayoutMain.setupWithViewPager(binding.viewPagerMain)
    }
}