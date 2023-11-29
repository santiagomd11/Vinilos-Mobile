package com.example.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.CollectorItemBinding
import com.example.vinilos.models.Collector

class CollectorsAdapter : RecyclerView.Adapter<CollectorsAdapter.CollectorViewHolder>(){
    var collectors: List<Collector> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val withDataBinding: CollectorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorsAdapter.CollectorViewHolder.LAYOUT,
            parent,
            false
        )
        return CollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        val collector = collectors[position]
        holder.viewDataBinding.also {
            it.collector = collector
        }

        holder.viewDataBinding.root.setOnClickListener { view ->
            val bundle = bundleOf("collectorId" to collector.collectorId, "name" to collector.name,
                "email" to collector.email, "telephone" to collector.telephone)
            view.findNavController().navigate(R.id.nav_artist_detail, bundle)
        }
    }


    override fun getItemCount(): Int {
        return collectors.size
    }



    class CollectorViewHolder(val viewDataBinding: CollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            val LAYOUT = R.layout.collector_item
        }
    }
}