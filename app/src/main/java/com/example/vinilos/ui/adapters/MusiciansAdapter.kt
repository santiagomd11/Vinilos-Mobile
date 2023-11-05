package com.example.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.ArtistItemBinding
import com.example.vinilos.models.Musician

class MusiciansAdapter : RecyclerView.Adapter<MusiciansAdapter.MusicianViewHolder>() {

    var musicians: List<Musician> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicianViewHolder {
        val withDataBinding: ArtistItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            MusicianViewHolder.LAYOUT,
            parent,
            false
        )
        return MusicianViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: MusicianViewHolder, position: Int) {
        val artist = musicians[position]
        holder.viewDataBinding.also {
            it.musician = artist
        }

        holder.viewDataBinding.root.setOnClickListener { view ->
            val bundle = bundleOf("artistId" to artist.id)
            view.findNavController().navigate(R.id.nav_artist_detail, bundle)
        }
    }


    override fun getItemCount(): Int {
        return musicians.size
    }

    class MusicianViewHolder(val viewDataBinding: ArtistItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            val LAYOUT = R.layout.artist_item
        }
    }
}