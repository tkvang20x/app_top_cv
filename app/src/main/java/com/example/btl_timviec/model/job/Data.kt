package com.example.btl_timviec.model.job

data class Data(
    val `data`: List<Any>,
    val jobs: List<Job>,
    val next_page: String,
    val prev_page: String
)