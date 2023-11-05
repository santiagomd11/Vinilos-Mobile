package com.example.vinilos.ui.artist_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.databinding.FragmentArtistDetailBinding


class ArtistDetailFragment : Fragment() {
    private var _binding: FragmentArtistDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val homeViewModel =
            ViewModelProvider(this).get(ArtistDetailViewModel::class.java)

        _binding = FragmentArtistDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val artistTitle: TextView = binding.artistTitle
        homeViewModel.artistTitle.observe(viewLifecycleOwner) {
            artistTitle.text = arguments?.getInt("artistId").toString()
        }

        val artistDescription: TextView = binding.artistDescription
        homeViewModel.artistDescription.observe(viewLifecycleOwner) {
            artistDescription.text = it
        }

        return root
    }

}

