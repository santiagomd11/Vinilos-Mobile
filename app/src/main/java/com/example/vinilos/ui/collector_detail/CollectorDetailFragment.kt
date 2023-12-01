package com.example.vinilos.ui.collector_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.databinding.FragmentCollectorDetailBinding

class CollectorDetailFragment : Fragment() {
    private var _binding: FragmentCollectorDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CollectorDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectorDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(CollectorDetailViewModel::class.java)

        arguments?.getInt("collectorId")?.let { collectorId ->
            if (collectorId != -1) {
                viewModel.fetchCollectorDetails(collectorId)
            }
        }

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.collectorDetails.observe(viewLifecycleOwner) { collector ->
            binding.collectorName.text = collector.name
            binding.collectorTelephone.text = "Telephone: ${collector.telephone}"
            binding.collectorEmail.text = "Email: ${collector.email}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
