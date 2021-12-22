package com.example.android.infinitescroll.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.infinitescroll.R
import com.example.android.infinitescroll.databinding.CharacterBinding
import com.example.android.infinitescroll.models.CharacterData

class MainAdapter(private val clickListener: MainClickListener) : PagingDataAdapter<CharacterData, MainAdapter.ViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: CharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharacterData) {
            binding.characterData = character
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: CharacterBinding =
            DataBindingUtil.inflate(inflater, R.layout.character, parent, false)
        return ViewHolder(view)
    }
}

class MainClickListener(val clickListener: (data: CharacterData) -> Unit) {
    fun onClick(data: CharacterData) = clickListener(data)
}