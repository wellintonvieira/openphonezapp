package br.com.wellintonvieira.openphonezapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.wellintonvieira.openphonezapp.data.models.History
import br.com.wellintonvieira.openphonezapp.databinding.HistoryItemsBinding

class HistoryAdapter(private val clickListener: (History, String) -> Unit):
    ListAdapter<History, HistoryAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HistoryItemsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ViewHolder(private val binding: HistoryItemsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: History, position: Int) {
            binding.apply {
                textViewHistoryPhoneNumber.text = "${item.phoneNumber}"
                cardViewItems.setOnClickListener {
                    clickListener(item, "click")
                }
                imageViewDelete.setOnClickListener {
                    clickListener(item, "delete")
                    notifyItemChanged(position)
                }
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<History>() {
    override fun areItemsTheSame(oldItem: History, newItem: History) = oldItem == newItem
    override fun areContentsTheSame(oldItem: History, newItem: History) = oldItem.phoneNumber == newItem.phoneNumber
}