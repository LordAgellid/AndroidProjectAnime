package com.inf5d6.tp1.ui.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inf5d6.tp1.R
import com.inf5d6.tp1.models.Season
import com.squareup.picasso.Picasso

class DetailsSeasonsRecyclerViewAdapter(private var datasetSeasons: MutableList<Season>) :
    RecyclerView.Adapter<DetailsSeasonsRecyclerViewAdapter.DetailsSeasonsViewHolder>(){
        class DetailsSeasonsViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsSeasonsViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.card_season, parent, false)
        return DetailsSeasonsViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DetailsSeasonsViewHolder, position: Int) {
        val imaCast = holder.view.findViewById<ImageView>(R.id.season)
        Picasso.get().load(this.datasetSeasons[position].imgURL).into(imaCast)

        val season_nb = holder.view.findViewById<TextView>(R.id.season_nb)
        season_nb.text = "Season ${this.datasetSeasons[position].number}"

        val episode_nb = holder.view.findViewById<TextView>(R.id.episodes_nb)
        episode_nb.text = "${this.datasetSeasons[position].episodeCount} episodes"
    }

    override fun getItemCount(): Int = this.datasetSeasons.size
}