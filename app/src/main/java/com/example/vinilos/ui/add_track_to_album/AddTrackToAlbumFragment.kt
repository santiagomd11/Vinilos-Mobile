package com.example.vinilos.ui.add_track_to_album

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.VolleyError
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentAddTrackToAlbumBinding
import com.example.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject
import java.util.regex.Pattern;


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

        arguments?.getInt("albumId")?.let{albumId ->
            if(albumId != null){
                viewModel.fetchAlbumDetails(albumId)
            }

        }

        binding.cancelSaveTrackButton.setOnClickListener{
            getActivity()?.onBackPressedDispatcher?.onBackPressed()
        }

        binding.saveTrackButton.setOnClickListener {
            val valid = validateForm()
            if(valid){
                val newTrack = JSONObject()
                newTrack.put("name", binding.textNameField.text.toString())
                newTrack.put("duration", binding.textLengthField.text.toString())
                NetworkServiceAdapter.instance?.addTrackToAlbum(body = newTrack, albumId = arguments?.getInt("albumId")!!, onComplete = {addTrackSuccess()}, onError = {addTrackFailure(it)})
            }

        }


        arguments?.getString("albumName")?.let{albumName ->
            if(albumName != null){
                binding.toolbar.setTitle("Album: $albumName")
            }

        }

        //observeViewModel()

        return binding.root
    }

    private fun validateForm():Boolean{
        var result = true
        if(TextUtils.isEmpty(binding.textNameField.text.toString())){
            binding.trackNameTextInputLayout.error = getString(R.string.requiredName)
            result = false
        }
        else{
            binding.trackNameTextInputLayout.error = null
        }
        if(TextUtils.isEmpty(binding.textLengthField.text.toString())){
            binding.trackLengthTextInputLayout.error = getString(R.string.requiredLength)
            result = false
        }
        else if(!validateTrackLength(binding.textLengthField.text.toString())){
            binding.trackLengthTextInputLayout.error = getString(R.string.invalid_track_length)
            result = false
        }
        else{
            binding.trackLengthTextInputLayout.error = null
        }
        return result
    }

    fun addTrackSuccess(){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.apply {
            setTitle("")
            setTitle(getString(R.string.success))
            setMessage(getString(R.string.track_added_success))
            setNeutralButton("OK") { _, _ ->
                getActivity()?.onBackPressedDispatcher?.onBackPressed()
                activity?.recreate()
            }
        }.create().show()
    }

    fun addTrackFailure(error: VolleyError){
        val alertDialog = AlertDialog.Builder(context)
        val errorMessage = JSONObject(String(error.networkResponse.data))["message"] as String
        alertDialog.apply {
            setTitle(getString(R.string.track_added_error))
            setMessage(errorMessage)
            setNeutralButton("Cerrar") { _, _ ->
                getActivity()?.onBackPressedDispatcher?.onBackPressed()
                activity?.recreate()
            }
        }.create().show()
    }

    fun validateTrackLength(length: String?): Boolean {
        val length_pattern = "([0-9]?[0-9]):[0-5][0-9]"
        val pattern = Pattern.compile(length_pattern)
        val matcher = pattern.matcher(length)
        return matcher.matches()
    }

    private fun observeViewModel() {
        viewModel.albumDetails.observe(viewLifecycleOwner, Observer { album ->
            // Now that we have all the details, let's update the UI
            //binding.toolbar.setTitle(album.name)


        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}