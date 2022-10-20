package com.inf5d6.tp1.models

data class DetailsTvShow(
    val episodeCount: Int,
    val genres: List<Genre>,
    val imgURL: String,
    val plot: String,
    val rating: Double,
    val roles: List<Role>,
    val seasons: List<Season>,
    val studio: Studio,
    val title: String,
    val tvParentalGuideline: String,
    val tvshowId: Int,
    val year: Int
)