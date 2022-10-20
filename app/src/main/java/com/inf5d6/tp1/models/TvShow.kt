package com.inf5d6.tp1.models

data class TvShow(
    val genres: List<Genre>,
    val imgURL: String,
    val rating: Double,
    val seasonCount: Int,
    val studio: Studio,
    val title: String,
    val tvParentalGuideline: String,
    val tvshowId: Int
)