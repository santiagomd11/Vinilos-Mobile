package com.example.vinilos.ui.create_album

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.VolleyError
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentCreateAlbumBinding
import com.example.vinilos.models.Album
import com.example.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject
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
            val valid = validateForm()
            if(valid){
                val newAlbum = JSONObject()
                newAlbum.put("name", binding.textNameField.text.toString())
                newAlbum.put("cover", binding.textCoverField.text.toString())
                newAlbum.put("releaseDate", binding.textReleaseDateField.text.toString())
                newAlbum.put("description",binding.textDescriptionField.text.toString())
                newAlbum.put("genre", binding.textGenreField.text.toString())
                newAlbum.put("recordLabel", binding.textLabelField.text.toString())
                NetworkServiceAdapter.instance?.createAlbum(body = newAlbum, onComplete = {createAlbumSuccess(it)}, onError = {createAlbumFailure(it)})
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

    private fun validateForm():Boolean{
        var result = true
        if(TextUtils.isEmpty(binding.textNameField.text.toString())){
            binding.nameTextInputLayout.error = getString(R.string.requiredName)
            result = false
        }
        else{
            binding.nameTextInputLayout.error = null
        }
        if(TextUtils.isEmpty(binding.textCoverField.text.toString())){
            binding.coverTextInputLayout.error = getString(R.string.requiredCover)
            result = false
        }
        else{
            binding.coverTextInputLayout.error = null
        }
        if(TextUtils.isEmpty(binding.textDescriptionField.text.toString())){
            binding.descriptionTextInputLayout.error = getString(R.string.requiredDescription)
            result = false
        }
        else{
            binding.descriptionTextInputLayout.error = null
        }
        if(TextUtils.isEmpty(binding.textReleaseDateField.text.toString())){
            binding.releaseDateTextInputLayout.error = getString(R.string.requiredReleaseDate)
            result = false
        }
        else{
            binding.releaseDateTextInputLayout.error = null
        }
        if(TextUtils.isEmpty(binding.textGenreField.text.toString())){
            binding.genreTextInputLayout.error = getString(R.string.requiredGenre)
            result = false
        }
        else{
            binding.genreTextInputLayout.error = null
        }
        if(TextUtils.isEmpty(binding.textLabelField.text.toString())){
            binding.labelTextInputLayout.error = getString(R.string.requiredRecordLabel)
            result = false
        }
        else{
            binding.labelTextInputLayout.error = null
        }
        return result
    }

    fun createAlbumSuccess(jsonObject: JSONObject){
        val alertDialog = AlertDialog.Builder(context)

        alertDialog.apply {
            //setIcon(R.drawable.ic_hello)
            setTitle("Hello")
            setMessage("Album creado exitosamente")
//            setPositiveButton("Positive") { _: DialogInterface?, _: Int ->
//                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
//            }
//            setNegativeButton("Negative") { _, _ ->
//                Toast.makeText(context, "Negative", Toast.LENGTH_SHORT).show()
//            }
            setNeutralButton("OK") { _, _ ->
                //Toast.makeText(context, "Neutral", Toast.LENGTH_SHORT).show()
                getActivity()?.onBackPressedDispatcher?.onBackPressed()
                activity?.recreate()
            }
//            setOnDismissListener {
//                Toast.makeText(context, "Hello!!!", Toast.LENGTH_SHORT).show()
//            }

        }.create().show()
    }

    fun createAlbumFailure(error: VolleyError){
        val alertDialog = AlertDialog.Builder(context)
        val errorMessage = JSONObject(String(error.networkResponse.data))["message"] as String
        alertDialog.apply {
            //setIcon(R.drawable.ic_hello)
            setTitle("Error al crear album")
            setMessage(errorMessage)
//            setPositiveButton("Positive") { _: DialogInterface?, _: Int ->
//                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
//            }
//            setNegativeButton("Negative") { _, _ ->
//                Toast.makeText(context, "Negative", Toast.LENGTH_SHORT).show()
//            }
            setNeutralButton("Neutral") { _, _ ->
                //Toast.makeText(context, "Cerrar", Toast.LENGTH_SHORT).show()
            }
//            setOnDismissListener {
//                Toast.makeText(context, "Hello!!!", Toast.LENGTH_SHORT).show()
//            }

        }.create().show()

        //
    }



}