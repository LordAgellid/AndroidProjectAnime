package com.inf5d6.tp1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inf5d6.tp1.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var rvListTvShow: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.inf5d6.tp1.R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        this.rvListTvShow = view.findViewById(R.id.homeRecyclerView)
        rvListTvShow.layoutManager = GridLayoutManager(this.context, 2)

        this.homeViewModel.tvShows.observe(viewLifecycleOwner){
            rvListTvShow.adapter = HomeRecyclerViewAdapter(it)
        }
    }
}