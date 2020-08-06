package com.example.rickandmortycharacters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.databinding.FragmentCharacterListBinding
import com.example.rickandmortycharacters.util.CharacterListRecyclerAdapter
import com.example.rickandmortycharacters.vm.MainActivityViewModel
import org.koin.android.ext.android.inject

class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private val characterListRecyclerAdapter = CharacterListRecyclerAdapter()
    private val viewModel: MainActivityViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_character_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.charactersListOnPage.observe(viewLifecycleOwner, Observer {
            characterListRecyclerAdapter.addListOfCharacters(it)
        })

        viewModel.downloadNextCharacterPage()
        binding.characterListRecyclerView.adapter = characterListRecyclerAdapter
        // TODO set recyclerview adapter

        // TODO trigger loading data when scrolled down (inject VM to recycler?)
    }
}