package br.com.wellintonvieira.openphonezapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.wellintonvieira.openphonezapp.databinding.ContactsItemsBinding

abstract class BaseAdapter<T>: RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    var items = emptyList<T>()

    class ViewHolder(val binding: ContactsItemsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ContactsItemsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bind(holder.binding, items[position])
    }

    override fun getItemCount() = items.size

    abstract fun bind(binding: ContactsItemsBinding, item: T)
}