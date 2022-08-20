package br.com.wellintonvieira.openphonezapp.ui.adapters

import android.annotation.SuppressLint
import br.com.wellintonvieira.openphonezapp.data.models.History
import br.com.wellintonvieira.openphonezapp.databinding.HistoryItemsBinding

class HistoryAdapter(val clickListener: (History, String) -> Unit): BaseAdapter<History>() {

    override fun bind(binding: HistoryItemsBinding, item: History, position: Int) {
        binding.apply {
            textViewHistoryPhoneNumber.text = "${item.phoneNumber}"
            cardViewItems.setOnClickListener {
                clickListener(item, "click")
            }
            imageViewDelete.setOnClickListener {
                notifyItemChanged(position)
                clickListener(item, "delete")
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setItems(items: List<History>) {
        this.listItems = items
        notifyDataSetChanged()
    }
}