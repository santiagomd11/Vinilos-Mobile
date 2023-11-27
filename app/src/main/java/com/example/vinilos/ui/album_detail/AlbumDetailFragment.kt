package com.example.vinilos.ui.album_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentAlbumDetailBinding
import java.text.SimpleDateFormat
import java.util.*

class AlbumDetailFragment : Fragment() {
    private var _binding: FragmentAlbumDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(AlbumDetailViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.album_detail)

        arguments?.getInt("albumId")?.let { albumId ->
            if (albumId != -1) {
                viewModel.fetchAlbumDetails(albumId)
            }
        }

        binding.fab1.setOnClickListener { view ->
            val album = viewModel.albumDetails.value
            val bundle = bundleOf(
                "albumId" to album?.albumId,
                "albumName" to album?.name,
                "albumDescription" to album?.description,
                "albumImage" to album?.cover,
                "albumGenre" to album?.genre,
                "albumReleaseDate" to album?.releaseDate,
                "albumRecordLabel" to album?.recordLabel
            )
            view.findNavController().navigate(R.id.action_nav_album_detail_to_nav_add_track_to_album, bundle)
        }

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.albumDetails.observe(viewLifecycleOwner, Observer { album ->
            // Now that we have all the details, let's update the UI
            binding.albumTitle.text = album.name
            Glide.with(this).load(album.cover).centerCrop().into(binding.albumImage)

            // Set genre, label, and release date texts
            binding.albumGenre.text = "Genero: ${album.genre}"
            binding.albumLabel.text = "Etiqueta: ${album.recordLabel}"
            binding.albumReleaseDate.text = "Fecha de lanzamiento: ${formatReleaseDate(album.releaseDate)}"

            // Lastly, set the description text
            for (track in album.tracks){
                binding.albumTracks.text = binding.albumTracks.text.toString() + "${track.name} - ${track.duration} \n"
            }

            binding.albumDescription.text = album.description
        })
    }
    private fun formatReleaseDate(releaseDate: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val date = inputFormat.parse(releaseDate)
            outputFormat.format(date)
        } catch (e: Exception) {
            Log.e("AlbumDetailFragment", "Error formatting date", e)
            releaseDate // Return original date string if there was an error
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
