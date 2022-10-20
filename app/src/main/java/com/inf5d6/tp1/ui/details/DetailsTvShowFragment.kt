package com.inf5d6.tp1.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inf5d6.tp1.R
import com.inf5d6.tp1.models.Role
import com.inf5d6.tp1.models.Season
import com.squareup.picasso.Picasso

class DetailsTvShowFragment : Fragment() {
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var rvListCast: RecyclerView
    private lateinit var rvListSeason: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idTvShow = arguments?.getInt("idTvShow")

        val detailsViewModelFactory = DetailsViewModelFactory(this.requireActivity().application, idTvShow!!)
        this.detailsViewModel = ViewModelProvider(this, detailsViewModelFactory)[DetailsViewModel::class.java]

        val tvShowPoster = view.findViewById<ImageView>(R.id.tvshow_poster)
        val tvShowTitle = view.findViewById<TextView>(R.id.tvshow_title)
        val tvShowYear = view.findViewById<TextView>(R.id.annee_publicaition)
        val tvShowNbEpisodes = view.findViewById<TextView>(R.id.episodes)
        val tvShowDescription = view.findViewById<TextView>(R.id.description)

        val iconNotFav = view.findViewById<ImageView>(R.id.favorite_icon)
        val iconisFav = view.findViewById<ImageView>(R.id.favorite_icon_filled)


        this.rvListCast = view.findViewById(R.id.cast_recyclerview)
        rvListCast.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        this.rvListSeason = view.findViewById(R.id.seasons_recyclerview)
        rvListSeason.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        detailsViewModel.detailsTvShow.observe(viewLifecycleOwner) {
            tvShowTitle.text = it.title
            tvShowYear.text = it.year.toString()
            tvShowNbEpisodes.text = it.episodeCount.toString()
            tvShowDescription.text = it.plot
            Picasso.get().load(it.imgURL).into(tvShowPoster)
            rvListCast.adapter = DetailsCastRecyclerViewAdapter(it.roles as MutableList<Role>)
            rvListSeason.adapter = DetailsSeasonsRecyclerViewAdapter(it.seasons as MutableList<Season>)
        }

        iconNotFav.setOnClickListener {
            iconNotFav.visibility = View.INVISIBLE
            iconisFav.visibility = View.VISIBLE
            this.detailsViewModel.addFavorite(idTvShow)
            println(idTvShow)
        }

        iconisFav.setOnClickListener{
            iconNotFav.visibility = View.VISIBLE
            iconisFav.visibility = View.INVISIBLE
            this.detailsViewModel.delFavorite(idTvShow)
            println(idTvShow)
        }

        detailsViewModel.isFavorite.observe(viewLifecycleOwner) {
            if(it == true) {
                iconNotFav.visibility = View.INVISIBLE
                iconisFav.visibility = View.VISIBLE
            }
        }




    }
}