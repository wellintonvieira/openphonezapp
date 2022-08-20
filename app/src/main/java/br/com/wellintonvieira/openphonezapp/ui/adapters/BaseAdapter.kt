package br.com.wellintonvieira.openphonezapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.wellintonvieira.openphonezapp.databinding.HistoryItemsBinding

abstract class BaseAdapter<T>: RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    protected var listItems = emptyList<T>()

    class ViewHolder(val binding: HistoryItemsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HistoryItemsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bind(holder.binding, listItems[position], position)
    }

    override fun getItemCount() = listItems.size

    abstract fun setItems(items: List<T>)

    abstract fun bind(binding: HistoryItemsBinding, item: T, position: Int)
}