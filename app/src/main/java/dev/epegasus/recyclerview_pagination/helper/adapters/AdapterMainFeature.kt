package dev.epegasus.recyclerview_pagination.helper.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.epegasus.recyclerview_pagination.R
import dev.epegasus.recyclerview_pagination.databinding.ListItemMainBinding

class AdapterMainFeature : ListAdapter<String, AdapterMainFeature.ViewHolderHome>(diffUtilString) {

    companion object {
        val diffUtilString = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolderHome(val binding: ListItemMainBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHome {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemMainBinding>(layoutInflater, R.layout.list_item_main, parent, false)
        return ViewHolderHome(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderHome, position: Int) {
        val item = getItem(position)
        holder.binding.tvTitleListItemMain.text = item
    }
}