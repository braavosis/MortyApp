package com.app.mortyapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.mortyapp.databinding.ItemDetailBinding

class RecyclerAdapter(private var characterList: List<Character>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDetailBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(characterList[position])

    }

    fun setCharacterList(characterList: List<Character>) {
        this.characterList = characterList
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemDetailBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            val itemName: TextView = binding.tvName
            val itemGender: TextView = binding.tvGender

            itemName.text = character.name
            itemGender.text = character.gender
        }
    }
}