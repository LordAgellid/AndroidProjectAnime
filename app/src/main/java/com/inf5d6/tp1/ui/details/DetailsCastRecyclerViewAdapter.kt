package com.inf5d6.tp1.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inf5d6.tp1.R
import com.inf5d6.tp1.models.Role
import com.squareup.picasso.Picasso

class DetailsCastRecyclerViewAdapter(private val datasetCast: MutableList<Role>) :
    RecyclerView.Adapter<DetailsCastRecyclerViewAdapter.DetailsCastViewHolder>() {
        class DetailsCastViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsCastViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.card_cast, parent, false)
        return DetailsCastViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsCastViewHolder, position: Int) {
        val imaCast = holder.view.findViewById<ImageView>(R.id.cast)
        Picasso.get().load(this.datasetCast[position].imgURL).into(imaCast)

        val cast_name = holder.view.findViewById<TextView>(R.id.cast_name)
        cast_name.text = "${this.datasetCast[position].name}"

        val cast_character_name = holder.view.findViewById<TextView>(R.id.cast_character_name)
        cast_character_name.text = "${this.datasetCast[position].character}"
    }

    override fun getItemCount(): Int = this.datasetCast.size
}