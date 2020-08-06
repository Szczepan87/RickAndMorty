package com.example.rickandmortycharacters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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
        viewModel.dataRetrievalError.observe(
            viewLifecycleOwner,
            Observer {
                if (!it.isNullOrBlank()) {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                }
            })

        binding.characterListRecyclerView.adapter = characterListRecyclerAdapter

        characterListRecyclerAdapter.onItemClickListener = {
            val action = CharacterListFragmentDirections
                .actionCharacterListFragmentToCharacterDetailsFragment(it)
            findNavController().navigate(action)
        }
        characterListRecyclerAdapter.onBottomReachedListener = {
            viewModel.downloadNextCharacterPage()
        }
    }
}