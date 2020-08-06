package com.example.rickandmortycharacters.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortycharacters.databinding.CharacterCardBinding
import com.example.rickandmortycharacters.model.Character


class CharacterListRecyclerAdapter :
    RecyclerView.Adapter<CharacterListRecyclerAdapter.CharacterViewHolder>() {

    private val charactersList = mutableListOf<Character>()
    var onItemClickListener: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharacterCardBinding.inflate(inflater)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int = charactersList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(charactersList[position])
    }

    fun addListOfCharacters(list: List<Character>) {
        charactersList.addAll(list)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(private val binding: CharacterCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(characterItem: Character) {
            binding.character = characterItem
            binding.characterCardConstraintLayout.setOnClickListener {
                onItemClickListener?.invoke(characterItem)
            }
        }

    }
}