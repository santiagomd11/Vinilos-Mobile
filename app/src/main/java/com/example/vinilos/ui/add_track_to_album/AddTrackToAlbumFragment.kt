package com.example.vinilos.ui.add_track_to_album

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import androidx.lifecycle.Observer
import com.example.vinilos.MainActivity
import com.example.vinilos.databinding.FragmentAddTrackToAlbumBinding
import com.example.vinilos.databinding.FragmentAlbumDetailBinding
import com.example.vinilos.databinding.FragmentAlbumsBinding
import com.example.vinilos.ui.adapters.AlbumsAdapter
import com.example.vinilos.ui.album_detail.AlbumDetailViewModel
import com.example.vinilos.ui.albums.AlbumsViewModel

class AddTrackToAlbumFragment : Fragment() {
    private var _binding: FragmentAddTrackToAlbumBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddTrackToAlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTrackToAlbumBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(AddTrackToAlbumViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.add_song)

        arguments?.getInt("albumId")?.let { albumId ->
            if (albumId != -1) {
                //viewModel.fetchAlbumDetails(albumId)
            }
        }

        arguments?.getString("albumName")?.let{albumName ->
            if(albumName != null){
                binding.albumName.text = albumName
            }

        }

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.albumDetails.observe(viewLifecycleOwner, Observer { album ->
            // Now that we have all the details, let's update the UI
            binding.albumName.text = album.name


        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}