package com.example.vinilos.ui.create_album

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentCreateAlbumBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class CreateAlbumFragment : Fragment() {
    private var _binding: FragmentCreateAlbumBinding? = null
    private val binding get() = _binding!!
    val myCalendar: Calendar = Calendar.getInstance()

    companion object {
        fun newInstance() = CreateAlbumFragment()
    }

    private lateinit var viewModel: CreateAlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val createAlbumViewModel =
            ViewModelProvider(this).get(CreateAlbumViewModel::class.java)

        _binding = FragmentCreateAlbumBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.cancelSaveAlbumButton.setOnClickListener{
            getActivity()?.onBackPressedDispatcher?.onBackPressed()
        }

        binding.saveAlbumButton.setOnClickListener {
            if(TextUtils.isEmpty(binding.textNameField.text.toString())){
                binding.nameTextInputLayout.error = getString(R.string.requiredName)
            }
            else{
                binding.nameTextInputLayout.error = null
            }
            if(TextUtils.isEmpty(binding.textCoverField.text.toString())){
                binding.coverTextInputLayout.error = getString(R.string.requiredCover)
            }
            else{
                binding.coverTextInputLayout.error = null
            }
            if(TextUtils.isEmpty(binding.textDescriptionField.text.toString())){
                binding.descriptionTextInputLayout.error = getString(R.string.requiredDescription)
            }
            else{
                binding.descriptionTextInputLayout.error = null
            }
            if(TextUtils.isEmpty(binding.textReleaseDateField.text.toString())){
                binding.releaseDateTextInputLayout.error = getString(R.string.requiredReleaseDate)
            }
            else{
                binding.releaseDateTextInputLayout.error = null
            }
            if(TextUtils.isEmpty(binding.textGenreField.text.toString())){
                binding.genreTextInputLayout.error = getString(R.string.requiredGenre)
            }
            else{
                binding.genreTextInputLayout.error = null
            }
            if(TextUtils.isEmpty(binding.textLabelField.text.toString())){
                binding.labelTextInputLayout.error = getString(R.string.requiredRecordLabel)
            }
            else{
                binding.labelTextInputLayout.error = null
            }

        }

        super.onCreate(savedInstanceState)
        val date =
            OnDateSetListener { view, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel()
            }
        binding.textReleaseDateField.setOnClickListener(View.OnClickListener {
            DatePickerDialog(
                this.context as Context,
                date,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        })

        return root
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        binding.textReleaseDateField.setText(dateFormat.format(myCalendar.time))
    }



}