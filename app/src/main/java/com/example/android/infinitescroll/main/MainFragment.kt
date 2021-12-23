package com.example.android.infinitescroll.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.android.infinitescroll.R
import com.example.android.infinitescroll.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    val alive: String = "alive"
    val dead: String = "dead"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = requireActivity()
        binding.fragment = this

        adapter = MainAdapter(MainClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainToDetail(it))
        })
        binding.charactersRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.status.observe(requireActivity(), Observer {
            adapter.refresh()
        })

        viewModel.toTop.observe(requireActivity(), Observer {
            it?.let {
                if (it == true) {
                    binding.charactersRecyclerView.scrollToPosition(0)
                    viewModel.sendScrollToTop()
                }
            }
        })

        lifecycleScope.launch {
            viewModel.listData.collectLatest {
                adapter.submitData(it)
            }
        }

        return binding.root
    }

    fun filterCharacters(status: String) {
        if (viewModel.status.value != status) {
            viewModel.updateStatus(status)
        }
    }
}