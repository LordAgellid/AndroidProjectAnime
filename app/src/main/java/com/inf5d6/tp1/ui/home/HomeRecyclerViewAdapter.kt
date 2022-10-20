package com.inf5d6.tp1.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.inf5d6.tp1.R
import com.inf5d6.tp1.models.TvShow
import com.squareup.picasso.Picasso

class HomeRecyclerViewAdapter(private val datasetTvShows:MutableList<TvShow>) :
    RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder>(){
    class HomeViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.card_tvshow, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val imaTvShow = holder.view.findViewById<ImageView>(R.id.poster)
        Picasso.get().load(this.datasetTvShows[position].imgURL).into(imaTvShow)
        holder.view.setOnClickListener{
            val param = bundleOf("idTvShow" to this.datasetTvShows[position].tvshowId)
            holder.view.findNavController().navigate(R.id.navigation_details, param)
        }
    }
    override fun getItemCount(): Int = this.datasetTvShows.size
}