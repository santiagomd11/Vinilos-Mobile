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
                binding.nameTextInputLayout.error = "El nombre es obligatorio"
            }
            else if(TextUtils.isEmpty(binding.textImageField.text.toString())){
                binding.imageTextInputLayout.error = "La imagen es obligatoria"
            }
            else if(TextUtils.isEmpty(binding.textDescriptionField.text.toString())){
                binding.descriptionTextInputLayout.error = "La descripcion es obligatoria"
            }
            else if(TextUtils.isEmpty(binding.textBirthDateField.text.toString())){
                binding.birthDateTextInputLayout.error = "La fecha de nacimiento es obligatoria"
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
        binding.textBirthDateField.setOnClickListener(View.OnClickListener {
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
        binding.textBirthDateField.setText(dateFormat.format(myCalendar.time))
    }



}