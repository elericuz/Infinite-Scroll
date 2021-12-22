package com.example.android.infinitescroll.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.android.infinitescroll.R
import com.example.android.infinitescroll.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = requireActivity()

        adapter = MainAdapter(MainClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainToDetail(it))
        })
        binding.charactersRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        lifecycleScope.launch {
            viewModel.listData.collect {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            viewModel.listData.collect {
                Log.d("infiniteScroll", "jojojo")
            }
        }

        return binding.root
    }
}