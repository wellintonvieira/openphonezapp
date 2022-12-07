package br.com.wellintonvieira.openphonezapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.wellintonvieira.openphonezapp.R
import br.com.wellintonvieira.openphonezapp.databinding.ActivityMainBinding
import br.com.wellintonvieira.openphonezapp.ui.adapters.PageAdapter
import br.com.wellintonvieira.openphonezapp.ui.fragments.HistoryFragment
import br.com.wellintonvieira.openphonezapp.ui.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var mainFragment: MainFragment
    private lateinit var historyFragment: HistoryFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        configureViewPager()
    }

    private fun configureViewPager() {
        val pageAdapter = PageAdapter(supportFragmentManager)
        mainFragment = MainFragment()
        historyFragment = HistoryFragment()
        pageAdapter.addFragment(mainFragment, getString(R.string.tab_title_phone))
        pageAdapter.addFragment(historyFragment, getString(R.string.tab_title_history))
        binding.viewPagerMain.adapter = pageAdapter
        binding.tabLayoutMain.setupWithViewPager(binding.viewPagerMain)
    }

    fun currentItem(currentItem: Int) {
        binding.viewPagerMain.currentItem = currentItem
    }

    fun pasteClipboard() {
        mainFragment.pasteClipboard()
    }
}