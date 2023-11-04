package com.example.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
        holder.viewDataBinding.also {
            it.musician = musicians[position]
        }
        // If you need to handle click events, you can do it here using holder.viewDataBinding.root
        // Example click event (commented out):
        /*
        holder.viewDataBinding.root.setOnClickListener {
            // Perform click handling, such as navigating to a detail screen.
        }
        */
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