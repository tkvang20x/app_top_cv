package com.example.btl_timviec.model.history

data class DataHistory(
    val `data`: List<Any>,
    val history: List<History>,
    val next_page: String,
    val prev_page: String
)