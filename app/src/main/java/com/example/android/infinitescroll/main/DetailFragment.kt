package com.example.android.infinitescroll.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.android.infinitescroll.R
import com.example.android.infinitescroll.databinding.FragmentDetailBinding
import com.example.android.infinitescroll.models.CharacterData

class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.fragment = this

        val character: CharacterData = DetailFragmentArgs.fromBundle(requireArguments()).character
        binding.character = character

        return binding.root
    }

    fun backButton() {
        findNavController().navigateUp()
    }
}