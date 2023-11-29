package com.example.vinilos.ui.collectors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.databinding.FragmentCollectorsBinding
import com.example.vinilos.models.Collector
import com.example.vinilos.models.Musician
import com.example.vinilos.ui.adapters.CollectorsAdapter
import com.example.vinilos.ui.adapters.MusiciansAdapter
import com.example.vinilos.ui.home.HomeViewModel

class CollectorsFragment : Fragment() {

    private var _binding: FragmentCollectorsBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CollectorsViewModel
    private var viewModelAdapter: CollectorsAdapter? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCollectorsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewModelAdapter = CollectorsAdapter()
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.collectorsRV// You need to add this RecyclerView to your layout
        val gridLayoutManager = GridLayoutManager(context, 2)

        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = viewModelAdapter
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, CollectorsViewModel.Factory(requireActivity().application)).get(
            CollectorsViewModel::class.java)
        viewModel.collector.observe(viewLifecycleOwner, Observer<List<Collector>> { collectors ->
            collectors?.apply {
                viewModelAdapter!!.collectors = this
            }
        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })


    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}