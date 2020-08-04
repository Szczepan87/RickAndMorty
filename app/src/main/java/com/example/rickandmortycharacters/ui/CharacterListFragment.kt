package com.example.rickandmortycharacters.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.databinding.FragmentCharacterListBinding

class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_character_list, container, false)
        return binding.root
    }
}